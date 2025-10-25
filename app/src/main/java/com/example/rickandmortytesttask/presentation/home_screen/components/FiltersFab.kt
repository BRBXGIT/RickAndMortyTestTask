package com.example.rickandmortytesttask.presentation.home_screen.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmortytesttask.presentation.theme.AppIcons
import com.example.rickandmortytesttask.presentation.theme.RickAndMortyTestTaskTheme

@Composable
fun FiltersFab(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(AppIcons.Filters),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun FiltersFabPreview() {
    RickAndMortyTestTaskTheme {
        FiltersFab(onClick = {})
    }
}