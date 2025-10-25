package com.example.rickandmortytesttask.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = background,
    onBackground = onBackground,

    surface = surface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,

    secondary = secondary,
    onSecondary = onSecondary,

    primary = primary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,

    error = error
)

@Composable
fun RickAndMortyTestTaskTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val mColors @Composable get() = MaterialTheme.colorScheme
val mShapes @Composable get() = MaterialTheme.shapes
val mTypography @Composable get() = MaterialTheme.typography