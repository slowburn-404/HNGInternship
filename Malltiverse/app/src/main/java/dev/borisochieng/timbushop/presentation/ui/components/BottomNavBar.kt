package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.borisochieng.timbushop.presentation.MainActivityViewModel
import dev.borisochieng.timbushop.presentation.ui.screens.CartScreen
import dev.borisochieng.timbushop.presentation.ui.screens.CheckoutScreen
import dev.borisochieng.timbushop.presentation.ui.screens.HomeScreen
import dev.borisochieng.timbushop.presentation.ui.screens.PaymentScreen
import dev.borisochieng.timbushop.presentation.ui.screens.PaymentSuccessfulScreen
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme


@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavItemClick: (NavItems) -> Unit,
    viewModel: MainActivityViewModel,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavItems.Home.route,
    ) {
        composable(route = NavItems.Home.route) {
            HomeScreen(viewModel = viewModel, innerPadding = innerPadding )
        }
        composable(route = NavItems.Cart.route) {
            CartScreen(innerPadding = innerPadding)
        }
        composable(route = NavItems.Checkout.route) {
            CheckoutScreen(innerPadding = innerPadding)
        }
//        composable(route = NavItems.Payment.route) {
//            PaymentScreen(innerPadding = innerPadding)
//        }
//        composable(route = NavItems.PaymentSuccess.route) {
//            PaymentSuccessfulScreen(innerPadding = innerPadding, navController = navController, onClick = {})
//        }
    }
}