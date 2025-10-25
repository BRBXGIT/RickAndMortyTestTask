package com.example.rickandmortytesttask.presentation.home_screen.screen

data class HomeScreenState(
    val isFiltersBSVisible: Boolean = false,

    val isSearching: Boolean = false,

    // Filters
    val name: String = "",
    val selectedStatus: String = "Doesn't matter",
    val selectedGender: String = "Doesn't matter",
    val selectedSpecies: String = "",
    val selectedType: String = ""
)
