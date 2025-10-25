@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortytesttask.presentation.home_screen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenIntent
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenState
import com.example.rickandmortytesttask.presentation.theme.AppIcons
import com.example.rickandmortytesttask.presentation.theme.RickAndMortyTestTaskTheme

@Composable
fun HomeScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    screenState: HomeScreenState,
    onIntent: (HomeScreenIntent) -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (screenState.isSearching) {
                IconButton(
                    onClick = { onIntent(HomeScreenIntent.ChangeIsSearching) }
                ) {
                    Icon(painter = painterResource(AppIcons.ArrowLeftFilled), contentDescription = null)
                }
            }
        },
        title = {
            if (screenState.isSearching) {
                SearchTextFiled(
                    query = screenState.name,
                    onQueryInput = { onIntent(HomeScreenIntent.ChangeName(it)) },
                    placeholder = "Enter name"
                )
            } else {
                Text(
                    text = "Search characters"
                )
            }
        },
        actions = {
            when {
                screenState.isSearching && screenState.name.isNotEmpty() -> {
                    IconButton(
                        onClick = { onIntent(HomeScreenIntent.ChangeName("")) }
                    ) {
                        Icon(painterResource(AppIcons.CloseCircle), contentDescription = null)
                    }
                }
                !screenState.isSearching -> {
                    IconButton(
                        onClick = { onIntent(HomeScreenIntent.ChangeIsSearching) }
                    ) {
                        Icon(painter = painterResource(AppIcons.Magnifier), contentDescription = null)
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun HomeScreenTopBarPreview() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    RickAndMortyTestTaskTheme {
        HomeScreenTopBar(scrollBehavior, HomeScreenState()) {}
    }
}

@Composable
private fun SearchTextFiled(
    query: String,
    onQueryInput: (String) -> Unit,
    placeholder: String
) {
    TextField(
        value = query,
        onValueChange = onQueryInput,
        singleLine = true,
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun SearchTextFiledPreview() {
    RickAndMortyTestTaskTheme {
        SearchTextFiled(
            query = "Some query",
            onQueryInput = {},
            placeholder = "Search"
        )
    }
}