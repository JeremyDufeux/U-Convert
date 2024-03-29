package com.jeremydufeux.u_convert.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DeepOrange,
    primaryVariant = LightOrange,
    secondary = EbonyClay,
    background = BrightGray,
    onSecondary = DeepOrange
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = DeepOrange,
    primaryVariant = LightOrange,
    secondary = DeepOrange,
    background = Color.White,
    onSecondary = Color.White,
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun UConvertTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
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