package dev.borisochieng.swipeshop.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.borisochieng.swipeshop.ui.SharedViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: SharedViewModel
) {
    val products by viewModel.allProducts.observeAsState(initial = emptyList())
    val cartProducts by viewModel.cartProducts.observeAsState(initial = emptyList())
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.Products.route
    ) {
        composable(route = BottomNavScreen.Products.route) {
            ProductsList(modifier = modifier, products = products) { updatedProduct ->
                viewModel.updateProducts(updatedProduct)
            }
        }
        composable(route = BottomNavScreen.CheckOut.route) {
            CheckoutList(
                modifier = modifier,
                cartProducts = cartProducts,
                onButtonClick = {
                    navController.navigate(route = "confirm_order") {
                        popUpTo(BottomNavScreen.CheckOut.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = "confirm_order") {
            ConfirmOrderScreen(navController = navController, onClick = {
                //remove products from cart and badge count
                cartProducts.forEach { product ->
                    viewModel.updateProducts(product.copy(isAddedToCart = false))
                }

                if (cartProducts.isEmpty()) {
                    //navigate to confirm order screen
                    navController.navigate(BottomNavScreen.Products.route) {
                        popUpTo(BottomNavScreen.Products.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            })
        }
    }
}