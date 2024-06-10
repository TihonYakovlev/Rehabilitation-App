package com.tihonyakovlev.rehabilitationapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tihonyakovlev.rehabilitationapp.presentation.darkMode

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = SoftBlue,
    primaryVariant = SoftGreen,
    secondary = SoftRed,
    background = LightGray,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = DarkGray,
    onSurface = DarkGray
)

private val DarkThemeColors = darkColors(
    primary = DarkGray,
    primaryVariant = LightGray,
    secondary = LightGray,
   // onPrimary = Color.Black
    )

@Composable
fun RehabilitationAppTheme(
    darkTheme: Boolean = darkMode,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkThemeColors
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}