package com.example.rickandmortytesttask.domain.home_screen

import androidx.paging.PagingData
import com.example.rickandmortytesttask.data.local.CharacterEntity
import com.example.rickandmortytesttask.data.network.home_screen.models.Result
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepo {

    fun getCharacters(filters: CharactersFilters = CharactersFilters()): Flow<PagingData<CharacterEntity>>
}