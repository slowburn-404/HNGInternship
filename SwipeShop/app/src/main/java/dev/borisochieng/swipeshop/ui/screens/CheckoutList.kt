package dev.borisochieng.swipeshop.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.borisochieng.swipeshop.data.Product

@Composable
fun CheckoutList(modifier: Modifier, cartProducts: List<Product>, onButtonClick: () -> Unit) {
    val scrollState = rememberScrollState()
    Log.d("Cart Products", cartProducts.toString())

    if (cartProducts.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "No Items In Cart",
                style = typography.titleMedium
            )
        }
    } else {

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
        ) {
            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                items(cartProducts) { product ->
                    ProductItem(
                        product = product
                    )
                }

            }

            Button(
                onClick = onButtonClick,
                modifier = Modifier.
                    padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Checkout",
                    style = typography.labelLarge,
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

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    val fakeProd = listOf(
        Product(
            name = "Product 1",
            isAddedToCart = true
        ),
        Product(
            name = "Product 2",
            isAddedToCart = true
        ),
        Product(
            name = "Product 3",
            isAddedToCart = true
        ),
        Product(
            name = "Product 4",
            isAddedToCart = true
        ),
        Product(
            name = "Product 5",
            isAddedToCart = true
        ),
        Product(
            name = "Product 6",
            isAddedToCart = true
        ),
    )
    CheckoutList(modifier = Modifier.fillMaxSize(), cartProducts = fakeProd, onButtonClick = {})
}