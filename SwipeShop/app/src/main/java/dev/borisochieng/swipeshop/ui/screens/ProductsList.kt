package dev.borisochieng.swipeshop.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.borisochieng.swipeshop.data.Product

@Composable
fun ProductsList(modifier: Modifier, products: List<Product>, onProductUpdated: (Product) -> Unit) {
    Log.d("Products", products.toString())

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(products) { product ->
            ProductItem(
                product = product,
            ) { updatedProduct ->
                onProductUpdated(updatedProduct)
            }
        }

    }
}

@Composable
fun ProductItem(product: Product, onButtonClick: (Product) -> Unit) {
    var isAddedToCart by remember {
        mutableStateOf(product.isAddedToCart)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.name,
                modifier = Modifier
                    .padding(16.dp),
                //.align(Alignment.CenterVertically),
                style = typography.titleMedium,
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    isAddedToCart = !isAddedToCart

                    onButtonClick(product.copy(isAddedToCart = isAddedToCart))
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = if (isAddedToCart) {
                        "Remove"
                    } else {
                        "Add"
                    },
                    style = typography.labelMedium
                )
                Icon(
                    imageVector = if (isAddedToCart)
                        Icons.Filled.ShoppingCart
                    else Icons.Outlined.ShoppingCart,
                    contentDescription = "Add to Cart"
                )

            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    //ProductScreen()
}