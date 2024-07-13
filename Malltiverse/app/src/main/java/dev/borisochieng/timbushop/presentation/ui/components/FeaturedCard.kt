package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.shape
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.typography

@Composable
fun FeaturedCard(modifier: Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .height(150.dp)
            .fillMaxWidth(),
        shape = shape.container,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape.container)
        ) {
            Image(
                painter = painterResource(id = R.drawable.headphones),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape.card),
                contentDescription = "Featured",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = "Premium Sound,",
                    style = typography.titleLarge,
                    color = Color.White
                )

                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = "Premium Savings",
                    style = typography.titleLarge,
                    color = Color.White
                )

                Text(
                    text = "Limited offer, hope on and get yours now",
                    style = typography.body,
                    color = Color.White
                )




            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun FeaturedCardPreview() {
    FeaturedCard(modifier = Modifier)
}