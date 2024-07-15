package dev.borisochieng.timbushop.presentation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.presentation.ui.components.BottomNavItems
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

        navController.navigate(BottomNavItems.Home.route) {
            popUpTo(BottomNavItems.Home.route) {
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
        Box(
            modifier = Modifier
                .background(
                    color = MalltiverseTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp),
                imageVector = Icons.Rounded.Check,
                tint = Color.White,
                contentDescription = stringResource(R.string.order_confirmed),
            )
        }
        Text(
            modifier = Modifier.padding(4.dp),
            text = stringResource(R.string.payment_successful),
            style = MalltiverseTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = stringResource(R.string.thank_you_for_your_purchase),
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