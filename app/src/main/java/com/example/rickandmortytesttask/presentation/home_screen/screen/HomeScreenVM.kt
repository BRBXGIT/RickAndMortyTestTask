package com.example.rickandmortytesttask.presentation.home_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortytesttask.domain.CharactersFilters
import com.example.rickandmortytesttask.domain.HomeScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeScreenVM @Inject constructor(
    repo: HomeScreenRepo
): ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        HomeScreenState()
    )

    private val characterFilters = _homeScreenState
        .map { state ->
            CharactersFilters(
                name = state.name,
                status = if (state.selectedStatus == "Doesn't matter") "" else state.selectedStatus,
                species = state.selectedSpecies,
                type = state.selectedType,
                gender = if (state.selectedGender == "Doesn't matter") "" else state.selectedGender
            )
        }
        .distinctUntilChanged()
    val characters = characterFilters
        .flatMapLatest { repo.getCharacters(it) }
        .cachedIn(viewModelScope)

    // === Private helpers ===
    private fun updateScreenState(transform: (HomeScreenState) -> HomeScreenState) {
        _homeScreenState.update(transform)
    }

    // === Intents ===
    fun sendIntent(intent: HomeScreenIntent) {
        when(intent) {
            // States
            HomeScreenIntent.ChangeFiltersBSVisible ->
                updateScreenState { it.copy(isFiltersBSVisible = !it.isFiltersBSVisible) }
            HomeScreenIntent.ChangeIsSearching ->
                updateScreenState { it.copy(isSearching = !it.isSearching) }

            // Filters
            is HomeScreenIntent.ChangeName ->
                updateScreenState { it.copy(name = intent.name) }
            is HomeScreenIntent.ChangeGender ->
                updateScreenState { it.copy(selectedGender = intent.gender) }
            is HomeScreenIntent.ChangeSpecies ->
                updateScreenState { it.copy(selectedSpecies = intent.species) }
            is HomeScreenIntent.ChangeStatus ->
                updateScreenState { it.copy(selectedStatus = intent.status) }
            is HomeScreenIntent.ChangeType ->
                updateScreenState { it.copy(selectedType = intent.type) }
        }
    }
}