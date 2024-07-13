package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.typography

@Composable
fun ScreenTitle(
    title: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterStart)
        )

        Text(
            text = title,
            style = typography.titleLarge,
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth(
                    align = Alignment.CenterHorizontally
                ),
            textAlign = TextAlign.Center
        )
    }


}

@Preview
@Composable
fun ScreenTitlePreview() {
    ScreenTitle(title = "Products List")

}