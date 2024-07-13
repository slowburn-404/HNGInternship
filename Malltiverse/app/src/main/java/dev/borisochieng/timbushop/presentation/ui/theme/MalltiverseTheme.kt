package dev.borisochieng.timbushop.presentation.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.ripple.LocalRippleTheme
//import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val colorScheme = MalltiverseColorScheme(
    background = background,
    onBackground = onBackground,
    primary = primary,
    onPrimary = onPrimary,
    container = container,
    onContainer = onContainer
)

private val typography = MalltiverseTypography(
    titleLarge = TextStyle(
        fontFamily = montserrat,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleNormal = TextStyle(
        fontFamily = montserrat,
        fontSize = 19.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodySmall = TextStyle(
        fontFamily = montserrat,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    body = TextStyle(
        fontFamily = montserrat,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyLarge = TextStyle(
        fontFamily = montserrat,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    labelNormal = TextStyle(
        fontFamily = montserrat,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    labelSmall = TextStyle(
        fontFamily = inter,
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal
    )
)

private val shape = MalltiverseShape(
    container = RoundedCornerShape(10.dp),
    outlinedButton = RoundedCornerShape(14.dp),
    button = RoundedCornerShape(12.dp),
    card = RoundedCornerShape(5.dp),
    bottomNav = RoundedCornerShape(20.dp)
)

private val size = MalltiverseSize(
    large = 24.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp
)

@Composable
fun MalltiverseTheme(
    //isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    //val colorScheme = colorScheme
    //val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalMalltiverseColorsScheme provides colorScheme,
        LocalMalltiverseTypography provides typography,
        LocalMalltiverseShape provides shape,
        LocalMalltiverseSize provides size,
        //LocalRippleTheme provides rippleIndication,
        content = content
    )
}

object MalltiverseTheme {
    val colorScheme: MalltiverseColorScheme
        @Composable
        get() = LocalMalltiverseColorsScheme.current

    val typography: MalltiverseTypography
        @Composable
        get() = LocalMalltiverseTypography.current

    val shape: MalltiverseShape
        @Composable
        get() = LocalMalltiverseShape.current

    val size: MalltiverseSize
        @Composable
        get() = LocalMalltiverseSize.current

}