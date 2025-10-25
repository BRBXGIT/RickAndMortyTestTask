package com.example.rickandmortytesttask.presentation.home_screen.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreen
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

fun NavGraphBuilder.homeScreen(
    homeScreenVM: HomeScreenVM,
    navController: NavController
) = composable<HomeScreenRoute> {
    val characters = homeScreenVM.characters.collectAsLazyPagingItems()
    val homeScreenState by homeScreenVM.homeScreenState.collectAsStateWithLifecycle()

    HomeScreen(
        characters = characters,
        screenState = homeScreenState,
        onIntent = homeScreenVM::sendIntent
    )
}