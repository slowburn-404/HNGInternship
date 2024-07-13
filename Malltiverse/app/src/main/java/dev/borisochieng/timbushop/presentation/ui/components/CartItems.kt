package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.shape

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    product: DomainProduct,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(shape.card)
            .background(MalltiverseTheme.colorScheme.background),
        shape = shape.card,
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(24.dp),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(product.imageURL)
                    .build(),
                contentDescription = product.name,
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    text = product.name,
                    style = MalltiverseTheme.typography.bodyLarge
                    )

                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    text = product.description,
                    style = MalltiverseTheme.typography.body
                )

            }

            Column {
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_home_unselected),
                    contentDescription = "Remove from cart"
                )

                Text(
                    text = product.price,
                    style = MalltiverseTheme.typography.bodyLarge
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    val fakeProduct = DomainProduct(
        name = "Ladies Leather Chic bag",
        description = "Office Trendy Handbag",
        price = "N 11,250",
        imageURL = "unsplash.com",
        category = emptyList()
    )
    CartItem(product = fakeProduct, modifier = Modifier , onClick = {})
}