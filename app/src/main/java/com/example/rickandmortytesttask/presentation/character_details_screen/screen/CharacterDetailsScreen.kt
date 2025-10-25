package com.example.rickandmortytesttask.presentation.character_details_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmortytesttask.presentation.theme.mColors

@Composable
fun CharacterDetailsScreen(
    screenState: CharacterDetailsScreenState,
    onIntent: (CharacterDetailsScreenIntent) -> Unit,
    characterId: Int
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mColors.background)
                .padding(innerPadding)
        ) {
            Text(
                text = "Details screen"
            )
        }
    }
}