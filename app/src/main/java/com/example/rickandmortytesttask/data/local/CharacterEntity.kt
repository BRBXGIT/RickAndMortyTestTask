package com.example.rickandmortytesttask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val image: String
)


