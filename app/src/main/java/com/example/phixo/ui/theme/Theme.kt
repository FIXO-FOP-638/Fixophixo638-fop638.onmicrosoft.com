package com.example.phixo.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val PhixoColorScheme = darkColorScheme(
    primary = ImperialMagenta,
    onPrimary = CosmicBlack,
    primaryContainer = CosmicSurfaceVariant,
    onPrimaryContainer = ImperialMagentaLight,
    secondary = SolarGold,
    onSecondary = CosmicBlack,
    secondaryContainer = CosmicSurfaceVariant,
    onSecondaryContainer = SolarGoldBright,
    tertiary = CyberCyan,
    onTertiary = CosmicBlack,
    background = CosmicBlack,
    onBackground = CosmicTextPrimary,
    surface = CosmicDark,
    onSurface = CosmicTextPrimary,
    surfaceVariant = CosmicSurface,
    onSurfaceVariant = CosmicTextSecondary,
    outline = CosmicBorder
)

@Composable
fun PhixoTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = CosmicBlack.toArgb()
            window.navigationBarColor = CosmicBlack.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = PhixoColorScheme,
        content = content
    )
}
