package com.example.rickandmortytesttask.domain.character_details_screen

import com.example.rickandmortytesttask.data.network.common.NetworkResponse
import com.example.rickandmortytesttask.data.network.details_screen.models.CharacterDetailsResponse

interface CharacterDetailsScreenRepo {

    suspend fun getCharacterDetails(id: Int): NetworkResponse<CharacterDetailsResponse>
}