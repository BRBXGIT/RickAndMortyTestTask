package com.example.rickandmortytesttask.domain.home_screen

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortytesttask.data.local.CharacterDb
import com.example.rickandmortytesttask.data.local.CharacterEntity
import com.example.rickandmortytesttask.data.network.home_screen.api.HomeScreenApiInstance
import com.example.rickandmortytesttask.data.network.home_screen.paging.CharactersRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HomeScreenRepoImpl @Inject constructor(
    private val api: HomeScreenApiInstance,
    private val characterDb: CharacterDb
): HomeScreenRepo {

    override fun getCharacters(filters: CharactersFilters): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = CharactersRemoteMediator(characterDb, api, filters),
            pagingSourceFactory = { characterDb.characterDao.pagingSource() }
        ).flow
    }
}