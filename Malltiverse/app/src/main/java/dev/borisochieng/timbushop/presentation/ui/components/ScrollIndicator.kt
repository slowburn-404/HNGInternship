package dev.borisochieng.timbushop.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme

@Composable
fun ScrollIndicator(scrollProgress: Float, totalItems: Int) {
    val dotSize = 10.dp
    val dotSpacing = 4.dp
    val activeDotColor = MalltiverseTheme.colorScheme.primary
    val inactiveDotColor = Color.Gray
    val dotBorderWidth = 1.dp

    val activeDotIndex = (scrollProgress * totalItems).toInt().coerceIn(0, totalItems - 1)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 16.dp, vertical = 4.dp),
        ,contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(dotSpacing)
        ) {
            repeat(totalItems) { index ->
                val isActive = index == activeDotIndex
                Box(
                    modifier = Modifier
                        .size(dotSize)
                        .background(
                            color = if (isActive) activeDotColor else Color.Transparent,
                            shape = CircleShape
                        )
                        .border(
                            width = if (isActive) 0.dp else dotBorderWidth,
                            color = inactiveDotColor,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}


