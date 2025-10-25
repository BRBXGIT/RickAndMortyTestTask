package com.example.rickandmortytesttask.data.network.common

data class NetworkException(
    val error: NetworkError,
    val label: String
): Exception()
