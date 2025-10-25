package com.example.rickandmortytesttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [CharacterEntity::class]
)
abstract class CharacterDb: RoomDatabase() {

    abstract val characterDao: CharacterDao
}