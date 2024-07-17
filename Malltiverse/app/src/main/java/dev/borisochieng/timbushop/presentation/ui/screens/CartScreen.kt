package dev.borisochieng.timbushop.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.presentation.MainActivityViewModel
import dev.borisochieng.timbushop.presentation.ui.components.CartItem
import dev.borisochieng.timbushop.presentation.ui.components.BottomNavItems
import dev.borisochieng.timbushop.presentation.ui.components.ShoppingSummary
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: MainActivityViewModel,
    innerPadding: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()
    val cartProducts = uiState.cartItems

    var promoCode by remember { mutableStateOf("") }

    val deliveryFee by remember { mutableDoubleStateOf(1500.00) }
    val discount by remember { mutableDoubleStateOf(1000.00) }
    val totalAmount  = viewModel.getTotalCartPrice()


    if(cartProducts.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            items(cartProducts) { cartItem ->
                CartItem(
                    product = cartItem,
                    onRemoveFromCart = {
                        viewModel.toggleCart(cartItem)
                    },
                    onQuantityChange = { product, newQuantity ->
                        viewModel.updateQuantity(product, newQuantity)
                        //totalAmount = cartItem.price + deliveryFee - discount
                    }
                )

            }

            item {
                ShoppingSummary(
                    modifier = Modifier.padding(8.dp),
                    promoCode = promoCode,
                    subTotal = totalAmount,
                    deliveryFee = deliveryFee,
                    discount = discount,
                    onValueChange = { promoCode = it },
                    onClick = {
                        navController.navigate(BottomNavItems.Checkout.route)
                    }
                )
            }

        }
    }
    else{
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = stringResource(R.string.no_items_in_cart),
                modifier = Modifier
                    .padding(8.dp),
                style = MalltiverseTheme.typography.bodyLarge
            )
        }
    }
}