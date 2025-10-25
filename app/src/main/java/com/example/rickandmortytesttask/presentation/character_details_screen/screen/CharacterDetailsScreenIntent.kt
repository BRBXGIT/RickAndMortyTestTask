package com.example.rickandmortytesttask.presentation.character_details_screen.screen

sealed interface CharacterDetailsScreenIntent {
    data class FetchDetails(val id: Int): CharacterDetailsScreenIntent
}