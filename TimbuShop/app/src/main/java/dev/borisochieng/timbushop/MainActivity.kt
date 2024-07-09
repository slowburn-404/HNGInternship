package dev.borisochieng.timbushop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.borisochieng.timbushop.ui.theme.TimbuShopTheme
import dev.borisochieng.timbushop.data.models.Product
import dev.borisochieng.timbushop.ui.MainActivityViewModel
import dev.borisochieng.timbushop.ui.MainActivityViewModelFactory
import dev.borisochieng.timbushop.util.Constants.API_KEY
import dev.borisochieng.timbushop.util.Constants.APP_ID
import dev.borisochieng.timbushop.util.Constants.BASE_IMAGE_URL
import dev.borisochieng.timbushop.util.Constants.ORGANIZATION_ID
import dev.borisochieng.timbushop.util.UIEvents
import dev.borisochieng.timbushop.util.UIState
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory((application as TimbuShopApplication).timbuAPIRepository)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by mainActivityViewModel.uiState.collectAsState()
            val snackBarHostState = remember { SnackbarHostState() }

            LaunchedEffect(Unit) {
                mainActivityViewModel.eventFlow.collect { event ->
                    if (event is UIEvents.SnackBarEvent) {
                        snackBarHostState.showSnackbar(event.message)
                    }
                }
            }

            TimbuShopTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MediumTopAppBar(
                            title = {
                                Text(
                                    "TimbuShop",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    Content(
                        uiState = uiState,
                        innerPadding = innerPadding,
                        onRefresh = {
                            mainActivityViewModel.getProducts(
                                apiKey = API_KEY,
                                organizationID = ORGANIZATION_ID,
                                appId = APP_ID
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Content(
    uiState: UIState,
    innerPadding: PaddingValues,
    onRefresh: () -> Unit
) {
    if (uiState.isLoading && uiState.errorMessage.isEmpty()) {
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(30.dp))
        }
    } else {
        ProductsList(
            productsList = uiState.products,
            modifier = Modifier.padding(innerPadding),
            onRefresh = onRefresh,
            isRefreshing = uiState.isLoading
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsList(
    productsList: List<Product>,
    modifier: Modifier,
    onRefresh: () -> Unit,
    lazyListState: LazyListState = rememberLazyListState(),
    isRefreshing: Boolean
) {
    val pullToRefreshState = rememberPullToRefreshState()
    if (productsList.isNotEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
        ) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(productsList) { product ->
                    ProductItem(product = product)
                }
            }

            //Refresh
            if (pullToRefreshState.isRefreshing) {
                LaunchedEffect(true) {
                    onRefresh()
                }
            }


            LaunchedEffect(isRefreshing) {
                if (isRefreshing) {
                    pullToRefreshState.startRefresh()
                } else {
                    pullToRefreshState.endRefresh()
                }

            }

            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }


    } else {
        EmptyState(modifier = modifier, onRetryClick = onRefresh)
    }
}

@Composable
fun EmptyState(modifier: Modifier, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Rounded.Refresh,
            modifier = Modifier
                .size(60.dp)
                .clickable { onRetryClick() },
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Refresh"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No products found, please try again.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("$BASE_IMAGE_URL${product.photos?.firstOrNull()?.url}")
                    .build(),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Fit,
            )

            CardHeader(
                product = product,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                text =
                "KES ${product.currentPrice.firstOrNull()?.kes?.first()}",
                style = MaterialTheme.typography.titleLarge
            )

        }
    }
}

@Composable
fun CardHeader(product: Product, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 6.dp)
                .align(Alignment.CenterVertically),
            text = product.name,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
                .align(Alignment.CenterVertically),
            text = if (product.availableQuantity?.roundToInt()!! > 0) "In Stock (${product.availableQuantity.roundToInt()})" else "Sold out",
            style = MaterialTheme.typography.bodyMedium,
            color = if (product.availableQuantity.roundToInt() > 0) Color.Gray else MaterialTheme.colorScheme.error
        )

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimbuShopTheme {
        ProductsList(
            productsList = emptyList(),
            modifier = Modifier,
            onRefresh = {},
            isRefreshing = false
        )
    }
}