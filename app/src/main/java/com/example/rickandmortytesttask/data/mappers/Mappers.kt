package com.example.rickandmortytesttask.data.mappers

import com.example.rickandmortytesttask.data.local.CharacterEntity
import com.example.rickandmortytesttask.data.network.home_screen.models.Result

fun Result.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        image = image
    )
}

fun CharacterEntity.toResult(): Result {
    return Result(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        image = image
    )
}