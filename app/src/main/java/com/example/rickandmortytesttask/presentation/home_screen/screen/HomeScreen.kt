@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortytesttask.presentation.home_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmortytesttask.data.network.home_screen.models.Result
import com.example.rickandmortytesttask.presentation.common.paging.PagingStatesContainer
import com.example.rickandmortytesttask.presentation.common.snackbars.SnackbarObserver
import com.example.rickandmortytesttask.presentation.common.snackbars.sendRetrySnackbar
import com.example.rickandmortytesttask.presentation.home_screen.components.CharacterItem
import com.example.rickandmortytesttask.presentation.home_screen.components.FiltersBS
import com.example.rickandmortytesttask.presentation.home_screen.components.FiltersFab
import com.example.rickandmortytesttask.presentation.home_screen.components.HomeScreenTopBar
import com.example.rickandmortytesttask.presentation.theme.mColors
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    characters: LazyPagingItems<Result>,
    screenState: HomeScreenState,
    onIntent: (HomeScreenIntent) -> Unit
) {
    // Snackbars stuff
    val snackbarHostState = remember { SnackbarHostState() }
    SnackbarObserver(snackbarHostState)

    val snackbarScope = rememberCoroutineScope()
    PagingStatesContainer(
        items = characters,
        onRetryRequest = { label, retry ->
            snackbarScope.launch { sendRetrySnackbar(label, retry) }
        },
    )

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FiltersFab(onClick = { onIntent(HomeScreenIntent.ChangeFiltersBSVisible) })
        },
        topBar = {
            HomeScreenTopBar(
                scrollBehavior = topAppBarScrollBehavior,
                screenState = screenState,
                onIntent = onIntent
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        if (screenState.isFiltersBSVisible) {
            FiltersBS(
                onIntent = onIntent,
                screenState = screenState
            )
        }

        PullToRefreshBox(
            isRefreshing = characters.loadState.refresh is LoadState.Loading,
            onRefresh = { characters.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .background(mColors.background)
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Fixed(2),
            ) {
                items(characters.itemCount) { index ->
                    val current = characters[index]

                    current?.let {
                        CharacterItem(
                            name = current.name,
                            status = current.status,
                            species = current.species,
                            gender = current.gender,
                            posterPath = current.image
                        )
                    }
                }
            }
        }
    }
}