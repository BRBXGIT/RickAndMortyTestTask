package com.example.rickandmortytesttask.presentation.common.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmortytesttask.data.network.common.NetworkException

@Composable
fun <T : Any> PagingStatesContainer(
    items: LazyPagingItems<T>,
    onRetryRequest: (label: String, retry: () -> Unit) -> Unit,
    onLoadingChange: (Boolean) -> Unit = {},
    onError: (Boolean) -> Unit = {}
) {
    val refreshState = items.loadState.refresh

    LaunchedEffect(refreshState) {
        val err = (refreshState as? LoadState.Error)?.error ?: return@LaunchedEffect
        val net = err as NetworkException
        onRetryRequest(net.label) { items.retry() }
    }

    LaunchedEffect(refreshState) {
        onLoadingChange(refreshState is LoadState.Loading)
    }

    LaunchedEffect(refreshState) {
        onError(refreshState is LoadState.Error)
    }
}