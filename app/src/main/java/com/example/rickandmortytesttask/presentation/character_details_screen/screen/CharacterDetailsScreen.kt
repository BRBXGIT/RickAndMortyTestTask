@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortytesttask.presentation.character_details_screen.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.rickandmortytesttask.presentation.character_details_screen.components.CharacterScreenHeader
import com.example.rickandmortytesttask.presentation.character_details_screen.components.CharacterScreenTopBar
import com.example.rickandmortytesttask.presentation.common.snackbars.SnackbarObserver
import com.example.rickandmortytesttask.presentation.theme.mColors
import com.example.rickandmortytesttask.presentation.theme.mShapes
import com.example.rickandmortytesttask.presentation.theme.mTypography

@Composable
fun CharacterDetailsScreen(
    screenState: CharacterDetailsScreenState,
    onIntent: (CharacterDetailsScreenIntent) -> Unit,
    characterId: Int
) {
    FetchDetails(onIntent, characterId)

    val snackbarHostState = remember { SnackbarHostState() }
    SnackbarObserver(snackbarHostState)

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CharacterScreenTopBar(
                scrollBehavior = topAppBarScrollBehavior,
                name = screenState.characterDetails?.name,
                onIntent = onIntent
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mColors.background)
        ) {
            val character = screenState.characterDetails

            character?.let {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        CharacterScreenHeader(
                            character = character,
                            topInnerPadding = innerPadding.calculateTopPadding() + 16.dp
                        )
                    }

                    item {
                        Text(
                            text = "Episodes: ",
                            style = mTypography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    items(character.episode) { url ->
                        EpisodeItem(url)
                    }
                }
            }
        }
    }
}

@Composable
private fun EpisodeItem(
    url: String
) {
    val context = LocalContext.current

    Text(
        text = url,
        style = mTypography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(mShapes.small)
            .clickable {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        url.toUri()
                    )
                )
            }
            .padding(4.dp)
    )
}

@Composable
private fun FetchDetails(
    onIntent: (CharacterDetailsScreenIntent) -> Unit,
    id: Int
) {
    LaunchedEffect(id) {
        onIntent(CharacterDetailsScreenIntent.FetchDetails(id))
    }
}