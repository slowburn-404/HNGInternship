package dev.borisochieng.timbushop.presentation.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PaymentScreen(
    innerPadding: PaddingValues,
) {
    Text(
        modifier = Modifier.padding(innerPadding),
        text = "Payment Screen"
    )
}