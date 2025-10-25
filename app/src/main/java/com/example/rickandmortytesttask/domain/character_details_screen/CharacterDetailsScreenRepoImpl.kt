package com.example.rickandmortytesttask.domain.character_details_screen

import com.example.rickandmortytesttask.data.network.common.NetworkRequest
import com.example.rickandmortytesttask.data.network.details_screen.api.CharacterDetailsScreenApiInstance
import javax.inject.Inject

class CharacterDetailsScreenRepoImpl @Inject constructor(
    private val api: CharacterDetailsScreenApiInstance
): CharacterDetailsScreenRepo {

    override suspend fun getCharacterDetails(id: Int) = NetworkRequest.exec { api.getCharacterDetails(id) }
}