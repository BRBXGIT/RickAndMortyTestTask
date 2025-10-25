package com.example.rickandmortytesttask

import app.cash.turbine.test
import com.example.rickandmortytesttask.domain.home_screen.HomeScreenRepo
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenIntent
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenState
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenVM
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeScreenVMTest {

    private val repo = mockk<HomeScreenRepo>()

    private lateinit var vm: HomeScreenVM

    @Before
    fun setUp() {
        vm = HomeScreenVM(repo)
    }

    @Test
    fun `state is default on start`() {
        assertEquals(vm.homeScreenState.value, HomeScreenState())
    }

    @Test
    fun `state updates correctly`() = runTest {
        vm.homeScreenState.test {
            val initial = awaitItem()
            assertFalse(initial.isFiltersBSVisible)
            assertFalse(initial.isSearching)
            assertEquals("", initial.name)

            vm.sendIntent(HomeScreenIntent.ChangeFiltersBSVisible)
            val afterFiltersBS = awaitItem()
            assertTrue(afterFiltersBS.isFiltersBSVisible)

            vm.sendIntent(HomeScreenIntent.ChangeIsSearching)
            val afterSearching = awaitItem()
            assertTrue(afterSearching.isSearching)

            vm.sendIntent(HomeScreenIntent.ChangeName("name"))
            val afterName = awaitItem()
            assertEquals("name", afterName.name)

            vm.sendIntent(HomeScreenIntent.ChangeGender("gender"))
            val afterGender = awaitItem()
            assertEquals("gender", afterGender.selectedGender)

            vm.sendIntent(HomeScreenIntent.ChangeSpecies("species"))
            val afterSpecies = awaitItem()
            assertEquals("species", afterSpecies.selectedSpecies)

            vm.sendIntent(HomeScreenIntent.ChangeStatus("status"))
            val afterStatus = awaitItem()
            assertEquals("status", afterStatus.selectedStatus)

            vm.sendIntent(HomeScreenIntent.ChangeType("type"))
            val afterType = awaitItem()
            assertEquals("type", afterType.selectedType)

            cancelAndIgnoreRemainingEvents()
        }
    }
}