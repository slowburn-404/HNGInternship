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
fun BottomNavGraph(navController: NavHostController, modifier: Modifier, viewModel: SharedViewModel) {
    val products by viewModel.allProducts.observeAsState(initial = emptyList())
    val cartProducts by viewModel.cartProducts.observeAsState(initial = emptyList())
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.Products.route
    ) {
        composable(route = BottomNavScreen.Products.route) {
            ProductsList(modifier = modifier, products = products ) { updatedProduct ->
                viewModel.updateProducts(updatedProduct)
            }
        }
        composable(route = BottomNavScreen.CheckOut.route) {
            CheckoutList(modifier = modifier, cartProducts = cartProducts)
        }
    }
}