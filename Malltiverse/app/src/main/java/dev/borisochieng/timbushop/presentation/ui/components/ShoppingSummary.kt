package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.shape

@Composable
fun ShoppingSummary(
    modifier: Modifier,
    promoCode: String,
    onValueChange: (String) -> Unit,
    subTotal: Double,
    deliveryFee: Double,
    discount: Double,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(MalltiverseTheme.colorScheme.container),
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = shape.card,
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.shopping_summary),
                style = MalltiverseTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = stringResource(R.string.discount_code),
                style = MalltiverseTheme.typography.body,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = promoCode,
                    onValueChange = onValueChange,
                    shape = shape.button,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(180.dp)
                        .height(50.dp)
                        .align(Alignment.CenterStart),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MalltiverseTheme.colorScheme.container,
                        unfocusedContainerColor = MalltiverseTheme.colorScheme.container,
                        focusedIndicatorColor = MalltiverseTheme.colorScheme.primary,
                        focusedLabelColor = MalltiverseTheme.colorScheme.onContainer,
                        unfocusedLabelColor = MalltiverseTheme.colorScheme.onContainer,
                        cursorColor = MalltiverseTheme.colorScheme.primary,
                        focusedTextColor = MalltiverseTheme.colorScheme.onContainer,
                        unfocusedTextColor = MalltiverseTheme.colorScheme.onContainer
                    )
                )

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .align(Alignment.CenterEnd),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MalltiverseTheme.colorScheme.primary,
                        contentColor = MalltiverseTheme.colorScheme.onPrimary
                    ),
                    shape = shape.button
                ) {
                    Text(
                        text = stringResource(R.string.apply)
                    )
                }

            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.sub_total),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )

                Text(
                    text = "₦ $subTotal",
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    fontWeight = FontWeight.SemiBold
                )


            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.delivery_fee),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )

                Text(
                    text = "₦ $deliveryFee",
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    fontWeight = FontWeight.SemiBold
                )


            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.discount_amount),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )

                Text(
                    text = "₦ $discount",
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    fontWeight = FontWeight.SemiBold
                )


            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(8.dp),
                thickness = 2.dp,
                color = Color.LightGray
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.total_amount),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )

                Text(
                    text = "₦ ${subTotal + deliveryFee - discount}",
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    fontWeight = FontWeight.SemiBold
                )


            }

            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MalltiverseTheme.colorScheme.primary,
                    contentColor = MalltiverseTheme.colorScheme.onPrimary
                ),
                shape = shape.button
            ) {

                Text(
                    text = stringResource(R.string.checkout)
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingSummaryPreview() {
    ShoppingSummary(
        modifier = Modifier,
        promoCode = "",
        onValueChange = {},
        deliveryFee = 1500.00,
        subTotal = 58610.00,
        discount = 35003.00,
        onClick = {}
    )

}