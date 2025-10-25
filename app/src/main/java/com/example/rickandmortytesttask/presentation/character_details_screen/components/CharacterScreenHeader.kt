package com.example.rickandmortytesttask.presentation.character_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.rickandmortytesttask.data.network.details_screen.models.CharacterDetailsResponse
import com.example.rickandmortytesttask.presentation.common.ui.AppAsyncImage
import com.example.rickandmortytesttask.presentation.theme.RickAndMortyTestTaskTheme
import com.example.rickandmortytesttask.presentation.theme.mColors
import com.example.rickandmortytesttask.presentation.theme.mShapes
import com.example.rickandmortytesttask.presentation.theme.mTypography
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource

@Composable
fun CharacterScreenHeader(
    character: CharacterDetailsResponse,
    topInnerPadding: Dp
) {
    val hazeState = remember { HazeState() }
    val posterHeight = 140.dp
    val contentPadding = 16.dp

    val hazeStyle = HazeStyle(
        backgroundColor = mColors.background,
        blurRadius = 8.dp,
        tint = HazeTint(
            color = mColors.background.copy(alpha = 0.5f),
            blendMode = BlendMode.SrcOver
        )
    )

    Box {
        CharacterBackground(
            imageUrl = character.image,
            hazeState = hazeState,
            hazeStyle = hazeStyle
        )

        CharacterInfo(
            character = character,
            topPadding = topInnerPadding,
            posterHeight = posterHeight,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun BoxScope.CharacterBackground(
    imageUrl: String,
    hazeState: HazeState,
    hazeStyle: HazeStyle
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(500)
            .size(Size.ORIGINAL)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.Low,
        modifier = Modifier
            .matchParentSize()
            .hazeSource(hazeState)
    )

    Box(
        modifier = Modifier
            .matchParentSize()
            .hazeEffect(state = hazeState, style = hazeStyle),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, mColors.background)
                    )
                )
        )
    }
}

@Composable
private fun CharacterInfo(
    character: CharacterDetailsResponse,
    topPadding: Dp,
    posterHeight: Dp,
    contentPadding: Dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(contentPadding),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = topPadding,
                start = contentPadding,
                end = contentPadding,
                bottom = contentPadding
            )
    ) {
        PosterImage(path = character.image, height = posterHeight)

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            InfoText("Status", character.status)
            InfoText("Species", character.species)
            InfoText("Type", character.type)
            InfoText("Gender", character.gender)
            InfoText("Origin", character.origin.name)
            InfoText("Location", character.location.name)
        }
    }
}

@Composable
private fun InfoText(label: String, value: String) {
    Text(
        text = "$label: $value",
        style = mTypography.bodyLarge.copy(color = mColors.onBackground)
    )
}

@Composable
private fun PosterImage(path: String, height: Dp) {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = height)
            .clip(mShapes.small)
    ) {
        AppAsyncImage(path)
    }
}

@Preview
@Composable
private fun CharacterScreenHeaderPreview() {
    RickAndMortyTestTaskTheme {
        CharacterScreenHeader(CharacterDetailsResponse(), 16.dp)
    }
}