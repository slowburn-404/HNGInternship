package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.shape
import java.util.UUID.randomUUID

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    product: DomainProduct,
    onRemoveFromCart: () -> Unit,
    onQuantityChange: (DomainProduct, Int) -> Unit
) {
    val subTotal by remember { mutableDoubleStateOf(product.price * product.quantity) }

    Card(
        modifier = modifier
            .height(150.dp)
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = shape.container,
        border = BorderStroke(2.dp, Color.LightGray)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(80.dp)
                    .align(Alignment.CenterVertically),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(product.imageURL)
                    .build(),
                contentDescription = product.name,
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(4.dp),
                            text = product.name,
                            style = MalltiverseTheme.typography.body,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Text(
                            modifier = Modifier
                                .padding(4.dp),
                            text = product.description,
                            style = MalltiverseTheme.typography.bodySmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp),
                        onClick = { onRemoveFromCart() },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.LightGray
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_trash),
                            contentDescription = "Remove from cart",
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.Start),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RectangleShape
                            )
                            .clickable {
                                if (product.quantity > 1) {
                                    val newQuantity = product.quantity - 1
                                    onQuantityChange(product, newQuantity)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(4.dp),
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = "Reduce quantity"
                        )
                    }

                    Text(
                        text = "${product.quantity}",
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp )
                            .align(Alignment.CenterVertically)
                    )

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RectangleShape
                            )
                            .clickable {
                                if(product.availableQuantity > product.quantity) {
                                    val newQuantity = product.quantity + 1
                                    onQuantityChange(product, newQuantity)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(4.dp),
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Add quantity"
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterVertically),
                        text = "₦ $subTotal",
                        style = MalltiverseTheme.typography.body,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    val fakeProduct = DomainProduct(
        id = randomUUID().toString(),
        name = "Ladies Leather Chic bag",
        description = "Office Trendy Handbag",
        price = 11250.00,
        imageURL = "unsplash.com",
        category = emptyList(),
        availableQuantity = 15,
        quantity = 1,
        isAddedToCart = false
    )
    CartItem(
        product = fakeProduct,
        modifier = Modifier,
        onRemoveFromCart = {},
        onQuantityChange = { _, _ -> }
    )
}