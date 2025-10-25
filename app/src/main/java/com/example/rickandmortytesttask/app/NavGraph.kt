package com.example.rickandmortytesttask.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortytesttask.presentation.home_screen.navigation.HomeScreenRoute
import com.example.rickandmortytesttask.presentation.home_screen.navigation.homeScreen
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenVM

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val homeScreenVM = hiltViewModel<HomeScreenVM>()

    NavHost(
        startDestination = HomeScreenRoute,
        navController = navController
    ) {
        homeScreen(
            homeScreenVM = homeScreenVM,
            navController = navController
        )
    }
}