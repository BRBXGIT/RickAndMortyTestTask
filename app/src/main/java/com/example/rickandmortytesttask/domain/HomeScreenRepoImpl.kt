package com.example.rickandmortytesttask.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortytesttask.data.network.home_screen.api.HomeScreenApiInstance
import com.example.rickandmortytesttask.data.network.home_screen.models.Result
import com.example.rickandmortytesttask.data.network.home_screen.paging.CharactersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeScreenRepoImpl @Inject constructor(
    private val api: HomeScreenApiInstance
): HomeScreenRepo {

    override fun getCharacters(filters: CharactersFilters): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CharactersPagingSource(api, filters) }
        ).flow
    }
}