package dev.borisochieng.timbushop.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.borisochieng.timbushop.R
import dev.borisochieng.timbushop.presentation.ui.components.OtherNavItems
import dev.borisochieng.timbushop.presentation.ui.components.PaymentCard
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme.shape

@Composable
fun PaymentScreen(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {

        PaymentCard(
            modifier = Modifier,
            cardNumber = cardNumber,
            expiryDate = expiryDate,
        )

        Text(
            text = stringResource(R.string.card_number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, bottom = 4.dp, end = 16.dp),
            style = MalltiverseTheme.typography.body,
            fontWeight = FontWeight.SemiBold
        )

        OutlinedTextField(
            value = cardNumber.chunked(4).joinToString(""),
            onValueChange = { if (it.length <= 16) cardNumber = it },
            shape = shape.button,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MalltiverseTheme.colorScheme.background,
                unfocusedContainerColor = MalltiverseTheme.colorScheme.background,
                focusedIndicatorColor = MalltiverseTheme.colorScheme.primary,
                cursorColor = MalltiverseTheme.colorScheme.primary,
            ),
            placeholder = {
                Text(
                    text = "0000 0000 0000 0000",
                    style = MalltiverseTheme.typography.body,
                    color = Color.LightGray
                )
            },
        )

        Row {
            Column {
                Text(
                    text = stringResource(R.string.expiry_date),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 4.dp, end = 16.dp),
                    style = MalltiverseTheme.typography.body,
                    fontWeight = FontWeight.SemiBold
                )

                OutlinedTextField(
                    value = expiryDate,
                    onValueChange = {
                        expiryDate = formatExpiryDate(it)
                    },
                    shape = shape.button,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .width(180.dp)
                        .height(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MalltiverseTheme.colorScheme.background,
                        unfocusedContainerColor = MalltiverseTheme.colorScheme.background,
                        focusedIndicatorColor = MalltiverseTheme.colorScheme.primary,
                        cursorColor = MalltiverseTheme.colorScheme.primary,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.expiry_date_placeholder),
                            style = MalltiverseTheme.typography.body,
                            color = Color.LightGray
                        )
                    },
                )
            }

            Column {
                Text(
                    text = stringResource(R.string.cvv),
                    modifier = Modifier//
                        .padding(start = 16.dp, top = 16.dp, bottom = 4.dp, end = 16.dp),
                    style = MalltiverseTheme.typography.body,
                    fontWeight = FontWeight.SemiBold
                )

                OutlinedTextField(
                    value = cvv,
                    onValueChange = {
                        if(it.length <= 3 ) cvv = it },
                    shape = shape.button,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .width(180.dp)
                        .height(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MalltiverseTheme.colorScheme.background,
                        unfocusedContainerColor = MalltiverseTheme.colorScheme.background,
                        focusedIndicatorColor = MalltiverseTheme.colorScheme.primary,
                        cursorColor = MalltiverseTheme.colorScheme.primary,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.cvv_place_holder),
                            style = MalltiverseTheme.typography.body,
                            color = Color.LightGray
                        )
                    },
                )
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            onClick = {
                navController.navigate(OtherNavItems.PaymentSuccess.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MalltiverseTheme.colorScheme.primary,
                contentColor = MalltiverseTheme.colorScheme.onPrimary
            ),
            shape = shape.button
        ) {

            Text(
                text = stringResource(R.string.make_payment)
            )
        }

    }
}

fun formatExpiryDate(date: String): String {
    //allow only digits and slash
    val formattedDate = date.replace(Regex("[^0-9/]"), "")
    //add slash after the second digit
    val finalDate = when {
        formattedDate.length == 2 && !formattedDate.contains("/") -> "$formattedDate/"
        formattedDate.length > 5 -> formattedDate.substring(0, 5)
        else -> formattedDate
    }

    return finalDate

}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(
        navController = NavController(LocalContext.current),
        innerPadding = PaddingValues(0.dp)
    )
}