package com.example.rickandmortytesttask.data.network.details_screen.models


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = ""
)