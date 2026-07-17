package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = LuxuryGold,
    secondary = Champagne,
    tertiary = DarkGold,
    background = CharcoalBg,
    surface = SurfaceCharcoal,
    surfaceVariant = SurfaceCharcoalElevated,
    onPrimary = CharcoalBg,
    onSecondary = CharcoalBg,
    onBackground = TextLinen,
    onSurface = TextLinen,
    onSurfaceVariant = TextOatmeal,
    outline = DarkGold
  )

private val LightColorScheme =
  lightColorScheme(
    primary = TextEspresso,
    secondary = LuxuryGold,
    tertiary = Champagne,
    background = SandBg,
    surface = SurfaceSand,
    surfaceVariant = SurfaceSandElevated,
    onPrimary = SandBg,
    onSecondary = SandBg,
    onBackground = TextEspresso,
    onSurface = TextEspresso,
    onSurfaceVariant = TextTruffle,
    outline = IvoryBorder
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Use our brand's premium palette by default
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
