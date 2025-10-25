package com.example.rickandmortytesttask.presentation.home_screen.screen

sealed interface HomeScreenIntent {
    // State
    data object ChangeFiltersBSVisible: HomeScreenIntent

    data object ChangeIsSearching: HomeScreenIntent

    // Filters
    data class ChangeName(val name: String): HomeScreenIntent
    data class ChangeStatus(val status: String): HomeScreenIntent
    data class ChangeGender(val gender: String): HomeScreenIntent
    data class ChangeSpecies(val species: String): HomeScreenIntent
    data class ChangeType(val type: String): HomeScreenIntent

    // Nav
    data class NavigateToCharacterDetails(val id: Int): HomeScreenIntent
}