package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.shape

@Composable
fun PaymentCard(
    modifier: Modifier,
    cardNumber: String,
    expiryDate: String
) {

    Card(
        modifier = modifier
            .padding(16.dp)
            .height(150.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MalltiverseTheme.colorScheme.onContainer
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape.container)
        ) {

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.TopEnd)
                    .rotate(30f)
                    .offset(y = (-15).dp, x = (-30).dp)
            ) {
                OverlappingEllipses(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Text(
                text = stringResource(R.string.visa),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd),
                style = MalltiverseTheme.typography.body,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = cardNumber.chunked(4).joinToString(" "),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterStart),
                style = MalltiverseTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.card_holder_name),
                        modifier = Modifier
                            .padding(2.dp),
                        style = MalltiverseTheme.typography.bodySmall,
                        color = Color.LightGray,
                    )

                    Text(
                        text = "Hafsat Ardo",
                        modifier = Modifier
                            .padding(2.dp),
                        style = MalltiverseTheme.typography.bodySmall,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )

                }

                Column {
                    Text(
                        text = stringResource(R.string.expiry_date),
                        modifier = Modifier
                            .padding(2.dp),
                        style = MalltiverseTheme.typography.bodySmall,
                        color = Color.LightGray,
                    )

                    Text(
                        text = expiryDate,
                        modifier = Modifier
                            .padding(2.dp),
                        style = MalltiverseTheme.typography.bodySmall,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )

                }

                Image(
                    painter = painterResource(id = R.drawable.ic_credit_card),
                    contentDescription = stringResource(id = R.string.visa),
                    modifier = Modifier.size(24.dp)
                )

            }


        }

    }

}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    PaymentCard(
        modifier = Modifier,
        cardNumber = "0000 0000 0000 0000",
        expiryDate = "00/00",
    )
}