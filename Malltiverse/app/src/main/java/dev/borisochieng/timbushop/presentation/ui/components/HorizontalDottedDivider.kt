package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDottedDivider(
    modifier: Modifier,
) {
    val spaceBetweenDots = 4.dp
    val color = Color.LightGray
    val dotRadius = 2.dp
    val strokeWidth = 1.dp

    Canvas(modifier = modifier) {
        val lineWidth = size.width
        val dotDiameter = dotRadius.toPx() * 2
        val spaceBetweenDotsPx = spaceBetweenDots.toPx()

        val paint = Paint().apply {
            this.color = color
            this.strokeCap = StrokeCap.Round
            this.strokeWidth = strokeWidth.toPx()
        }

        var xOffset = 0f
        while (xOffset < lineWidth) {
            drawCircle(
                color = color,
                radius = dotRadius.toPx(),
                center = Offset(xOffset, size.height / 2)
            )
            xOffset += dotDiameter + spaceBetweenDotsPx
        }
    }
}
