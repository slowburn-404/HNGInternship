package dev.borisochieng.timbushop.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.borisochieng.timbushop.TimbuShopApplication
import dev.borisochieng.timbushop.data.models.Product
import dev.borisochieng.timbushop.presentation.ui.components.BottomNavBar
import dev.borisochieng.timbushop.presentation.ui.components.NavItems
import dev.borisochieng.timbushop.presentation.ui.components.ScreenTitle
import dev.borisochieng.timbushop.presentation.ui.components.getIcons
import dev.borisochieng.timbushop.presentation.ui.screens.HomeScreen
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.typography
import dev.borisochieng.timbushop.util.Constants.API_KEY
import dev.borisochieng.timbushop.util.Constants.APP_ID
import dev.borisochieng.timbushop.util.Constants.BASE_IMAGE_URL
import dev.borisochieng.timbushop.util.Constants.ORGANIZATION_ID
import dev.borisochieng.timbushop.util.UIEvents
import dev.borisochieng.timbushop.util.UIState
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory((application as TimbuShopApplication).timbuAPIRepositoryImpl)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by mainActivityViewModel.uiState.collectAsState()
            val snackBarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val screens = listOf(
                NavItems.Home,
                NavItems.Cart,
                NavItems.Checkout,
                NavItems.Payment,
                NavItems.PaymentSuccess
            )

            val navCurrentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen =
                screens.find { it.route == navCurrentBackStackEntry?.destination?.route }

            val selectedItemIndex =
                screens.indexOfFirst { it.route == currentScreen?.route }.takeIf { it >= 0 } ?: 0

            LaunchedEffect(Unit) {
                mainActivityViewModel.eventFlow.collect { event ->
                    if (event is UIEvents.SnackBarEvent) {
                        snackBarHostState.showSnackbar(event.message)
                    }
                }
            }

            MalltiverseTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                ScreenTitle(title = currentScreen?.title ?: "Malltiverse")
                            }
                        )
                    },
                    bottomBar = {
                        val icons = getIcons()
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(16.dp)
                                .background(
                                    color = MalltiverseTheme.colorScheme.onBackground,
                                    shape = MalltiverseTheme.shape.bottomNav
                                )
                                .shadow(elevation = 4.dp, shape = MalltiverseTheme.shape.bottomNav),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .height(90.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                screens.forEachIndexed { index, bottomNavScreen ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            if (selectedItemIndex != index) {
                                                navController.navigate(bottomNavScreen.route) {
                                                    popUpTo(navController.graph.startDestinationId) {
                                                        inclusive = false
                                                    }
                                                    launchSingleTop = true
                                                }
                                            }
                                        },
                                        icon = {
                                            val icon = icons[bottomNavScreen.route]

                                            Box(
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .background(
                                                        color = if (selectedItemIndex == index) MalltiverseTheme.colorScheme.primary else Color.Transparent,
                                                        shape = CircleShape
                                                    )
                                                    .padding(8.dp),
                                               // contentAlignment = Alignment.Center
                                            ) {
                                                if (icon != null) {
                                                    Icon(
                                                        imageVector = icon,
                                                        contentDescription = bottomNavScreen.title,
                                                        tint = if (selectedItemIndex == index) Color.Black else Color.White,
                                                    )
                                                }

                                            }

                                        },
                                        interactionSource = remember { MutableInteractionSource() },
                                        alwaysShowLabel = false,
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = Color.Transparent,
                                            unselectedIconColor = Color.Transparent,
                                            indicatorColor = Color.Transparent
                                        )

                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    BottomNavBar(
                        navController = navController,
                        onNavItemClick = {},
                        viewModel = mainActivityViewModel,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}