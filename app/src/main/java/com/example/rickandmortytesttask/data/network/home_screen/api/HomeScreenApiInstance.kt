package com.example.rickandmortytesttask.data.network.home_screen.api

import com.example.rickandmortytesttask.data.network.home_screen.models.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeScreenApiInstance {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String = "",
        @Query("status") status: String = "",
        @Query("species") species: String = "",
        @Query("type") type: String = "",
        @Query("gender") gender: String = ""
    ): Response<CharactersResponse>
}