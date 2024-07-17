package dev.borisochieng.timbushop.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.presentation.MainActivityViewModel
import dev.borisochieng.timbushop.presentation.ui.components.FeaturedCard
import dev.borisochieng.timbushop.presentation.ui.components.LazyRowWithScrollIndicator
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.colorScheme
import dev.borisochieng.timbushop.util.UIEvents

@Composable
fun HomeScreen(
    viewModel: MainActivityViewModel,
    innerPadding: PaddingValues,
    snackBarHostState: SnackbarHostState
) {
    val uiState by viewModel.uiState.collectAsState()
    val categories = uiState.categoriesWithProducts
    val lazyColumnState = rememberLazyListState()

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
                state = lazyColumnState,
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {

                item {
                    FeaturedCard(modifier = Modifier)
                }
                items(categories.keys.toList(), key = { it } ) { categoryName ->
                    LazyRowWithScrollIndicator(
                        categoryName = categoryName,
                        products = categories[categoryName] ?: emptyList(),
                        viewModel = viewModel
                    )
                }
            }

        }

        if (uiState.errorMessage.isNotEmpty()) {
            LaunchedEffect(Unit) {
                viewModel.eventFlow.collect{ event->
                    when(event) {
                        is UIEvents.SnackBarEvent -> {
                            snackBarHostState.showSnackbar(event.message)
                        }

                    }
                }
            }
        }
    }

}