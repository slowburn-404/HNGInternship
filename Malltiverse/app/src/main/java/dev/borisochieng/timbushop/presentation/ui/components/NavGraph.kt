package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.borisochieng.timbushop.presentation.MainActivityViewModel
import dev.borisochieng.timbushop.presentation.ui.screens.CartScreen
import dev.borisochieng.timbushop.presentation.ui.screens.CheckoutScreen
import dev.borisochieng.timbushop.presentation.ui.screens.HomeScreen
import dev.borisochieng.timbushop.presentation.ui.screens.PaymentScreen
import dev.borisochieng.timbushop.presentation.ui.screens.PaymentSuccessfulScreen


@Composable
fun BottomNavBar(
    navController: NavHostController,
    viewModel: MainActivityViewModel,
    innerPadding: PaddingValues,
    snackBarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItems.Home.route,
    ) {
        composable(route = BottomNavItems.Home.route) {
            HomeScreen(viewModel = viewModel, innerPadding = innerPadding, snackBarHostState = snackBarHostState )
        }
        composable(route = BottomNavItems.Cart.route) {
            CartScreen(innerPadding = innerPadding, viewModel = viewModel, navController = navController)
        }
        composable(route = BottomNavItems.Checkout.route) {
            CheckoutScreen(innerPadding = innerPadding, navController = navController)
        }
        composable(route = OtherNavItems.Payment.route) {
            PaymentScreen(innerPadding = innerPadding, navController = navController)
        }
        composable(route = OtherNavItems.PaymentSuccess.route) {
            PaymentSuccessfulScreen(innerPadding = innerPadding, navController = navController, onClick = {} )
        }
    }
}