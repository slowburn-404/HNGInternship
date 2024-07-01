package dev.borisochieng.swipeshop.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "Products") {
        composable("Products") { ProductScreen(modifier = modifier) }
        composable("Checkout") { CheckoutScreen(modifier = modifier) }
    }
}