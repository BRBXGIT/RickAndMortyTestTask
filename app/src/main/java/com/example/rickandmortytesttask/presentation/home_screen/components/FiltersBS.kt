@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortytesttask.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenIntent
import com.example.rickandmortytesttask.presentation.home_screen.screen.HomeScreenState
import com.example.rickandmortytesttask.presentation.theme.RickAndMortyTestTaskTheme
import com.example.rickandmortytesttask.presentation.theme.mShapes
import com.example.rickandmortytesttask.presentation.theme.mTypography

private val statuses = listOf("Alive", "Dead", "Unknown", "Doesn't matter")
private val genders = listOf("Male", "Female", "Genderless", "Unknown", "Doesn't matter")

@Composable
fun FiltersBS(
    onIntent: (HomeScreenIntent) -> Unit,
    screenState: HomeScreenState
) {
    ModalBottomSheet(
        dragHandle = {},
        shape = mShapes.small,
        onDismissRequest = { onIntent(HomeScreenIntent.ChangeFiltersBSVisible) }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                FilterTextField(
                    value = screenState.selectedSpecies,
                    onValueChange = { onIntent(HomeScreenIntent.ChangeSpecies(it)) },
                    label = "Species"
                )
            }

            item {
                FilterTextField(
                    value = screenState.selectedType,
                    onValueChange = { onIntent(HomeScreenIntent.ChangeType(it)) },
                    label = "Type"
                )
            }

            item { FilterText("Status: ") }

            items(statuses) { status ->
                RadioFilter(
                    selected = screenState.selectedStatus,
                    current = status,
                    onClick = { onIntent(HomeScreenIntent.ChangeStatus(status)) }
                )
            }

            item { FilterText("Gender: ") }

            items(genders) { gender ->
                RadioFilter(
                    selected = screenState.selectedGender,
                    current = gender,
                    onClick = { onIntent(HomeScreenIntent.ChangeGender(gender)) }
                )
            }
        }
    }
}

@Composable
private fun RadioFilter(
    selected: String,
    current: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RadioButton(
            selected = selected == current,
            onClick = onClick
        )

        Text(
            text = current,
            style = mTypography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun RadioFilterPreview() {
    RickAndMortyTestTaskTheme {
        RadioFilter(
            selected = "TEXT",
            current = "TEXT",
            onClick = {},
        )
    }
}

@Composable
private fun FilterText(
    label: String
) {
    Text(
        text = label,
        style = mTypography.bodyLarge
    )
}

@Preview
@Composable
private fun FilterTextPreview() {
    RickAndMortyTestTaskTheme {
        FilterText("Gender")
    }
}

@Composable
private fun FilterTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        label = {
            Text(
                text = label
            )
        }
    )
}

@Preview
@Composable
private fun FilterTextFieldPreview() {
    RickAndMortyTestTaskTheme {
        FilterTextField(
            value = "Text",
            onValueChange = {},
            label = "Some label"
        )
    }
}