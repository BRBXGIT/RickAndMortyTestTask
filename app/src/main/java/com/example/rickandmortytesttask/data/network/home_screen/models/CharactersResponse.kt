package com.example.rickandmortytesttask.data.network.home_screen.models


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("results")
    val results: List<Result> = listOf()
)