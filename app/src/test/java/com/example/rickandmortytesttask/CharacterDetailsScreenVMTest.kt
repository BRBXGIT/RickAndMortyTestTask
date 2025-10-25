@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.rickandmortytesttask

import app.cash.turbine.test
import com.example.rickandmortytesttask.data.network.common.NetworkErrors
import com.example.rickandmortytesttask.data.network.common.NetworkResponse
import com.example.rickandmortytesttask.data.network.details_screen.models.CharacterDetailsResponse
import com.example.rickandmortytesttask.domain.character_details_screen.CharacterDetailsScreenRepo
import com.example.rickandmortytesttask.presentation.character_details_screen.screen.CharacterDetailsScreenIntent
import com.example.rickandmortytesttask.presentation.character_details_screen.screen.CharacterDetailsScreenState
import com.example.rickandmortytesttask.presentation.character_details_screen.screen.CharacterDetailsScreenVM
import com.example.rickandmortytesttask.presentation.common.snackbars.SnackbarController
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharacterDetailsScreenVMTest {

    private lateinit var vm: CharacterDetailsScreenVM

    private val dispatcher = StandardTestDispatcher()
    private val repo = mockk<CharacterDetailsScreenRepo>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        vm = CharacterDetailsScreenVM(repo, dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `state is default on start`() {
        assertEquals(vm.characterDetailsScreenState.value, CharacterDetailsScreenState())
    }

    @Test
    fun `fetch character details updates state on success`() = runTest {
        coEvery { repo.getCharacterDetails(1) } returns NetworkResponse(
            response = CharacterDetailsResponse(id = 1),
            error = NetworkErrors.SUCCESS,
            label = "Success"
        )

        vm.characterDetailsScreenState.test {
            val initial = awaitItem()
            assertNull(initial.characterDetails)

            vm.sendIntent(CharacterDetailsScreenIntent.FetchDetails(1))
            advanceUntilIdle()

            val after = awaitItem()
            assertEquals(1, after.characterDetails?.id)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetch character details make a snackbar on error`() = runTest {
        coEvery { repo.getCharacterDetails(1) } returns NetworkResponse(
            response = CharacterDetailsResponse(id = 1),
            error = NetworkErrors.UNAUTHORIZED,
            label = "Unauthorized"
        )

        SnackbarController.events.test {
            vm.sendIntent(CharacterDetailsScreenIntent.FetchDetails(1))
            advanceUntilIdle()

            val after = awaitItem()

            assertNotNull(after.action)

            cancelAndIgnoreRemainingEvents()
        }
    }
}