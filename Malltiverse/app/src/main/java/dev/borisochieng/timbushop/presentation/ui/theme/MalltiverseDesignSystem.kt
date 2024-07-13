package dev.borisochieng.timbushop.presentation.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class MalltiverseColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val container: Color,
    val onContainer: Color,
)

data class MalltiverseTypography(
    val titleLarge: TextStyle,
    val titleNormal: TextStyle,
    val body: TextStyle,
    val bodyLarge: TextStyle,
    val bodySmall: TextStyle,
    val labelNormal: TextStyle,
    val labelSmall: TextStyle,
)

data class MalltiverseShape(
    val container: Shape,
    val outlinedButton: Shape,
    val button: Shape,
    val card: Shape,
    val bottomNav: Shape,
)

data class MalltiverseSize(
    val large: Dp,
    val medium: Dp,
    val normal: Dp,
    val small: Dp,
)

val LocalMalltiverseColorsScheme = staticCompositionLocalOf {
    MalltiverseColorScheme(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        container = Color.Unspecified,
        onContainer = Color.Unspecified
    )
}

val LocalMalltiverseTypography = staticCompositionLocalOf {
    MalltiverseTypography(
        titleLarge = TextStyle.Default,
        titleNormal = TextStyle.Default,
        bodySmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        body = TextStyle.Default,
        labelSmall = TextStyle.Default,
        labelNormal = TextStyle.Default
    )
}

val LocalMalltiverseShape = staticCompositionLocalOf {
    MalltiverseShape(
        container = RectangleShape,
        outlinedButton = RectangleShape,
        button = RectangleShape,
        card = RectangleShape,
        bottomNav = RectangleShape
    )

}

val LocalMalltiverseSize = staticCompositionLocalOf {
    MalltiverseSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        small = Dp.Unspecified,
    )
}