package dev.borisochieng.swipeshop.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.borisochieng.swipeshop.MainActivity

@Composable
fun ConfirmOrderScreen(navController: NavHostController, onClick: () -> Unit) {

    val context = LocalContext.current

    //listen for back button press
    val backPressedDispatcher = (context as MainActivity).onBackPressedDispatcher

    val backCallback = rememberUpdatedState {
        //clear cart and navigate up
        onClick()

        navController.navigate(BottomNavScreen.Products.route) {
            popUpTo(BottomNavScreen.Products.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    BackHandler {
        backCallback.value()
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
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Order confirmed",
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Order confirmed!",
            style = typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Thank you for shopping with SwipeShop.",
            style = typography.bodyLarge,
            color = Color.Gray,
            fontWeight = FontWeight.Normal

            )
        Text(
            modifier = Modifier
                .padding(top = 24.dp)
                .clickable(onClick = onClick),
            text = "Continue Shopping",
            style = typography.bodyLarge.copy(
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary,
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ConfirmOrderScreenPreview() {
    //ConfirmOrderScreen(onClick = {})
}