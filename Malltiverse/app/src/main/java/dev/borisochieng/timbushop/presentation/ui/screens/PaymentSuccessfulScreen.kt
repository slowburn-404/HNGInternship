package dev.borisochieng.timbushop.presentation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.borisochieng.timbushop.presentation.ui.components.NavItems
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme

@Composable
fun PaymentSuccessfulScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onClick: () -> Unit
) {
    //listen for back button press
    val backPressCallback = rememberUpdatedState {
        //clear cart
        onClick()

        navController.navigate(NavItems.Home.route) {
            popUpTo(NavItems.Home.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    BackHandler {
        backPressCallback.value()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp),
            imageVector = Icons.Rounded.Check,
            tint = MalltiverseTheme.colorScheme.primary,
            contentDescription = "Order confirmed",
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Payment Successful",
            style = MalltiverseTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Thank you for your purchase",
            style = MalltiverseTheme.typography.body,

            )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentSuccessfulScreenPreview() {
    PaymentSuccessfulScreen(innerPadding = PaddingValues(), navController = NavHostController(
        LocalContext.current
    ), onClick = {})
}