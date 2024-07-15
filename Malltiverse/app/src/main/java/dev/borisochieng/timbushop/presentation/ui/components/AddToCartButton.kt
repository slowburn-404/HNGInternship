package dev.borisochieng.timbushop.presentation.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme

@Composable
fun AddToCartButton(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MalltiverseTheme.colorScheme.onBackground
        ),
        shape = MalltiverseTheme.shape.outlinedButton,
        border = BorderStroke(
            width = 1.dp,
            color = MalltiverseTheme.colorScheme.primary)
    ) {
        Text(
            text = label,
            style = MalltiverseTheme.typography.body
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AddToCartButtonPreview() {
    AddToCartButton(label = "Add to cart", onClick = {}, modifier = Modifier)
}