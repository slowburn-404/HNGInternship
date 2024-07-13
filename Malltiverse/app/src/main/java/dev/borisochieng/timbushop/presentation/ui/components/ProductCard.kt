package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.colorScheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.shape

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: DomainProduct,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(200.dp)
            .fillMaxHeight()
            .clip(shape.card),
        shape = shape.card,
        colors = CardDefaults.cardColors(colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.container)
                    .clip(shape.card)
                    .height(180.dp)
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
            }

            //product details
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    text = product.name,
                    style = MalltiverseTheme.typography.bodyLarge,
                    color = colorScheme.onBackground,
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = product.description,
                    style = MalltiverseTheme.typography.body,
                    color = colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = product.price,
                    style = MalltiverseTheme.typography.labelNormal,
                    color = colorScheme.primary
                )

                AddToCartButton(
                    modifier = modifier
                        .padding(4.dp),
                    label = stringResource(id = R.string.add_to_cart),
                    onClick = onClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    val fakeProduct = DomainProduct(
        name = "Joystick Game Controller",
        description = "Wired UCOM USB Pad",
        price = "N 11,250",
        imageURL = "unsplash.com",
        category = emptyList()
    )
    ProductCard(modifier = Modifier, product = fakeProduct, onClick = {})

}