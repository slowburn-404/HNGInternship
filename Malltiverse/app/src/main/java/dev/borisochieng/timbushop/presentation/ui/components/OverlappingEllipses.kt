package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OverlappingEllipses(
    modifier: Modifier
) {
    val ellipsisSize = 200f
    Canvas(modifier = modifier
    ) {
        // Draw first ellipse
        drawOval(
            color = Color(0xA0FAFAFA), // Hex color with alpha
            topLeft = Offset(0f, 0f),
            size = Size(ellipsisSize, ellipsisSize)
        )

        // Draw second ellipse
        drawOval(
            color = Color(0xA0FAFAFA), // Hex color with alpha
            topLeft = Offset(100f, 0f),
            size = Size(ellipsisSize, ellipsisSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OverlappingEllipsesPreview() {
    OverlappingEllipses(modifier = Modifier)
}
