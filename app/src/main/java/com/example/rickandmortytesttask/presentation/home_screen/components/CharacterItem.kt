package com.example.rickandmortytesttask.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmortytesttask.presentation.common.ui.AppAsyncImage
import com.example.rickandmortytesttask.presentation.theme.RickAndMortyTestTaskTheme
import com.example.rickandmortytesttask.presentation.theme.mColors
import com.example.rickandmortytesttask.presentation.theme.mTypography

@Composable
fun CharacterItem(
    name: String,
    status: String,
    species: String,
    gender: String,
    posterPath: String
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = mColors.surfaceContainer,
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AppAsyncImage(posterPath)

            CharacterItemFooter(name, species, gender, status)
        }
    }
}

@Composable
private fun BoxScope.CharacterItemFooter(
    name: String,
    species: String,
    gender: String,
    status: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.End)
                .clip(RoundedCornerShape(topStart = 16.dp))
                .background(mColors.secondary)
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (status == "Alive") mColors.primary else mColors.error
                    )
            )

            Text(
                text = status,
                style = mTypography.labelLarge.copy(color = mColors.onSecondary)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(mColors.surfaceContainer)
                .clip(
                    RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                ),
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = name,
                style = mTypography.bodyLarge.copy(color = mColors.onSurfaceVariant)
            )

            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "$gender | $species",
                style = mTypography.bodyMedium.copy(color = mColors.onSecondary)
            )
        }
    }
}

@Preview
@Composable
private fun CharacterItemPreview() {
    RickAndMortyTestTaskTheme {
        CharacterItem(
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            posterPath = ""
        )
    }
}