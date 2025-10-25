@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortytesttask.presentation.character_details_screen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmortytesttask.presentation.character_details_screen.screen.CharacterDetailsScreenIntent
import com.example.rickandmortytesttask.presentation.theme.AppIcons
import com.example.rickandmortytesttask.presentation.theme.RickAndMortyTestTaskTheme
import com.example.rickandmortytesttask.presentation.theme.mColors

@Composable
fun CharacterScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    name: String?,
    onIntent: (CharacterDetailsScreenIntent) -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = name ?: "Loading..."
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onIntent(CharacterDetailsScreenIntent.NavigateUp) }
            ) {
                Icon(
                    painter = painterResource(AppIcons.ArrowLeftFilled),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = mColors.surfaceContainer.copy(alpha = 0f),
            scrolledContainerColor = mColors.surfaceContainer,
            titleContentColor = mColors.onBackground,
            navigationIconContentColor = mColors.onBackground
        ),
    )
}

@Preview
@Composable
private fun CharacterScreenTopBarPreview() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    RickAndMortyTestTaskTheme {
        CharacterScreenTopBar(
            scrollBehavior = scrollBehavior,
            name = "Rick",
            onIntent = {}
        )
    }
}