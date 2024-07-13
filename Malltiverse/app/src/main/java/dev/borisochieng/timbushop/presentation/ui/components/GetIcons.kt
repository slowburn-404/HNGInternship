package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import dev.borisochieng.timbushop.R

@Composable
fun getIcons (): Map<String, ImageVector> =
    mapOf(
        "home" to ImageVector.vectorResource(id = R.drawable.ic_home_unselected),
        "cart" to ImageVector.vectorResource(id = R.drawable.ic_shopping_cart_unselected),
        "checkout" to ImageVector.vectorResource(id = R.drawable.ic_checkout_unselected)
    )