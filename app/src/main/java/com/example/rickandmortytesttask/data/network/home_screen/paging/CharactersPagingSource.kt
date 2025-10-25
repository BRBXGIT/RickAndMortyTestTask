package com.example.rickandmortytesttask.data.network.home_screen.paging

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortytesttask.data.network.common.NetworkErrors
import com.example.rickandmortytesttask.data.network.common.NetworkException
import com.example.rickandmortytesttask.data.network.common.NetworkRequest
import com.example.rickandmortytesttask.data.network.home_screen.api.HomeScreenApiInstance
import com.example.rickandmortytesttask.data.network.home_screen.models.Result
import com.example.rickandmortytesttask.domain.home_screen.CharactersFilters

class CharactersPagingSource(
    private val apiInstance: HomeScreenApiInstance,
    private val filters: CharactersFilters
): PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1

        val response = NetworkRequest.exec {
            apiInstance.getCharacters(
                page = page,
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
            val prevPage = getPageNumber(result.info.prev)

            LoadResult.Page(
                data = result.results,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } else {
            LoadResult.Error(NetworkException(response.error!!, response.label!!))
        }
    }
}

private fun getPageNumber(url: String?): Int? {
    val uri = url?.toUri()
    val pageValue = uri?.getQueryParameter("page")
    val pageNumber = pageValue?.toIntOrNull()

    return pageNumber
}