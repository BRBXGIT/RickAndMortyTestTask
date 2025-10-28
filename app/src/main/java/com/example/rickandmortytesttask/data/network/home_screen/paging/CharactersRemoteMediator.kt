package com.example.rickandmortytesttask.data.network.home_screen.paging

import androidx.core.net.toUri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmortytesttask.data.local.CharacterDb
import com.example.rickandmortytesttask.data.local.CharacterEntity
import com.example.rickandmortytesttask.data.mappers.toCharacterEntity
import com.example.rickandmortytesttask.data.network.common.NetworkErrors
import com.example.rickandmortytesttask.data.network.common.NetworkException
import com.example.rickandmortytesttask.data.network.common.NetworkRequest
import com.example.rickandmortytesttask.data.network.home_screen.api.HomeScreenApiInstance
import com.example.rickandmortytesttask.domain.home_screen.CharactersFilters

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val characterDb: CharacterDb,
    private val apiInstance: HomeScreenApiInstance,
    private val filters: CharactersFilters
): RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val loadKey = when(loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    1
                } else {
                    (lastItem.id / state.config.pageSize) + 1
                }
            }
        }

        val response = NetworkRequest.exec {
            apiInstance.getCharacters(
                page = loadKey,
                name = filters.name,
                status = filters.status,
                species = filters.species,
                type = filters.type,
                gender = filters.gender
            )
        }

        return if (response.error == NetworkErrors.SUCCESS) {
            val result = response.response!!

            val nextPage = getPageNumber(result.info.next)

            characterDb.withTransaction {
                if (loadType == LoadType.REFRESH) characterDb.characterDao.clearAll()
                val characterEntities = result.results.map { it.toCharacterEntity() }
                characterDb.characterDao.upsertAll(characterEntities)
            }

            MediatorResult.Success(endOfPaginationReached = nextPage == null)
        } else {
            MediatorResult.Error(NetworkException(response.error!!, response.label!!))
        }
    }
}

private fun getPageNumber(url: String?): Int? {
    val uri = url?.toUri()
    val pageValue = uri?.getQueryParameter("page")
    val pageNumber = pageValue?.toIntOrNull()

    return pageNumber
}