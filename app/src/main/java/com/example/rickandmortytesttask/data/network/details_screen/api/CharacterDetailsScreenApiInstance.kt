package com.example.rickandmortytesttask.data.network.details_screen.api

import com.example.rickandmortytesttask.data.network.details_screen.models.CharacterDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailsScreenApiInstance {

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Response<CharacterDetailsResponse>
}