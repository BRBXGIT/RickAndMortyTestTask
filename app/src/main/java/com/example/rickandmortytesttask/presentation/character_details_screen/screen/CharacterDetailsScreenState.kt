package com.example.rickandmortytesttask.presentation.character_details_screen.screen

import com.example.rickandmortytesttask.data.network.details_screen.models.CharacterDetailsResponse

data class CharacterDetailsScreenState(
    val isLoading: Boolean = false,

    val characterDetails: CharacterDetailsResponse? = null
)
