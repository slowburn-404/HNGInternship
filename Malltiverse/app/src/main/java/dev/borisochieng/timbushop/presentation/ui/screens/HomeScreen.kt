package dev.borisochieng.timbushop.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.presentation.MainActivityViewModel
import dev.borisochieng.timbushop.presentation.ui.components.FeaturedCard
import dev.borisochieng.timbushop.presentation.ui.components.LazyRowWithScrollIndicator
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.colorScheme
import dev.borisochieng.timbushop.util.Constants.API_KEY
import dev.borisochieng.timbushop.util.Constants.APP_ID
import dev.borisochieng.timbushop.util.Constants.ORGANIZATION_ID

@Composable
fun HomeScreen(
    viewModel: MainActivityViewModel,
    innerPadding: PaddingValues,
) {
    val uiState by viewModel.uiState.collectAsState()
    val categories by viewModel.categories.collectAsState()
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp),
                    color = colorScheme.primary
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(vertical = 8.dp),
            ) {
                item {
                    FeaturedCard(modifier = Modifier)
                }
                items(categories.keys.toList()) { categoryName ->
                    LazyRowWithScrollIndicator(
                        categoryName = categoryName,
                        products = categories[categoryName] ?: emptyList()
                    )
                }
            }

        }

        if (uiState.errorMessage.isNotEmpty()) {
            Snackbar(
                modifier = Modifier
                    .padding(16.dp),
                action = {
                    Button(
                        onClick = {
                            viewModel.getProducts(
                                apiKey = API_KEY,
                                organizationID = ORGANIZATION_ID,
                                appId = APP_ID
                            )
                        }
                    ) {
                        Text("Retry")
                    }

                }
            ) {
                Text(text = uiState.errorMessage)
            }
        }
    }

}