package dev.borisochieng.swipeshop.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.borisochieng.swipeshop.data.Product

@Composable
fun CheckoutList(modifier: Modifier, cartProducts: List<Product>) {
    Log.d("Cart Products", cartProducts.toString())

    if (cartProducts.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "No Items In Cart",
                style = typography.titleMedium
            )
        }
    } else {

        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(cartProducts) { product ->
                ProductItem(
                    product = product
                )
            }

        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = product.name,
            modifier = Modifier
                .padding(16.dp),
            style = typography.titleMedium,
        )


    }
}