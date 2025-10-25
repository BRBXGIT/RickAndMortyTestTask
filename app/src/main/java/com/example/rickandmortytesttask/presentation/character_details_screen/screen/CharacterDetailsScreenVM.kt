package com.example.rickandmortytesttask.presentation.character_details_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortytesttask.data.network.common.NetworkErrors
import com.example.rickandmortytesttask.di.AppDispatcher
import com.example.rickandmortytesttask.di.Dispatcher
import com.example.rickandmortytesttask.domain.character_details_screen.CharacterDetailsScreenRepo
import com.example.rickandmortytesttask.presentation.common.snackbars.sendRetrySnackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsScreenVM @Inject constructor(
    private val repo: CharacterDetailsScreenRepo,
    @Dispatcher(AppDispatcher.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    private val _characterDetailsScreenState = MutableStateFlow(CharacterDetailsScreenState())
    val characterDetailsScreenState = _characterDetailsScreenState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1),
        CharacterDetailsScreenState()
    )

    // === Private helpers ===
    private fun updateState(transform: (CharacterDetailsScreenState) -> CharacterDetailsScreenState) {
        _characterDetailsScreenState.update(transform)
    }

    // === Data ===
    private fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch(dispatcherIo) {
            updateState { it.copy(isLoading = true) }

            val response = repo.getCharacterDetails(id)
            if (response.error == NetworkErrors.SUCCESS) {
                val details = response.response
                updateState {
                    it.copy(
                        isLoading = false,
                        characterDetails = details
                    )
                }
            } else {
                updateState { it.copy(isLoading = false) }
                sendRetrySnackbar(
                    label = response.label!!,
                    action = { fetchCharacterDetails(id) }
                )
            }
        }
    }

    fun sendIntent(intent: CharacterDetailsScreenIntent) {
        when(intent) {
            is CharacterDetailsScreenIntent.FetchDetails -> fetchCharacterDetails(intent.id)
        }
    }
}