package com.example.rickandmortytesttask.presentation.character_details_screen.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.rickandmortytesttask.presentation.character_details_screen.screen.CharacterDetailsScreen
import com.example.rickandmortytesttask.presentation.character_details_screen.screen.CharacterDetailsScreenIntent
import com.example.rickandmortytesttask.presentation.character_details_screen.screen.CharacterDetailsScreenVM
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsScreenRoute(val id: Int)

fun NavGraphBuilder.characterDetailsScreenRoute(
    navController: NavController
) = composable<CharacterDetailsScreenRoute> {
    val characterDetailsScreenVM = hiltViewModel<CharacterDetailsScreenVM>()
    val characterDetailsScreenState by characterDetailsScreenVM.characterDetailsScreenState.collectAsStateWithLifecycle()

    val characterId = it.toRoute<CharacterDetailsScreenRoute>().id

    CharacterDetailsScreen(
        screenState = characterDetailsScreenState,
        characterId = characterId,
        onIntent = { intent ->
            when(intent) {
                CharacterDetailsScreenIntent.NavigateUp -> navController.navigateUp()
                else -> characterDetailsScreenVM.sendIntent(intent)
            }
        },
    )
}