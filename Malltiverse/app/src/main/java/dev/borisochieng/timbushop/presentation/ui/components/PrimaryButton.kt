package dev.borisochieng.timbushop.presentation.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MalltiverseTheme.colorScheme.primary,
            contentColor = MalltiverseTheme.colorScheme.onPrimary
        ),
        shape = MalltiverseTheme.shape.button
    ) {
        Text(
            text = label,
            style = MalltiverseTheme.typography.labelNormal
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(label = "Checkout", onClick = {})
}