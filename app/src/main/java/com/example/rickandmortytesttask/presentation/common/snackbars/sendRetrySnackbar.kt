package com.example.rickandmortytesttask.presentation.common.snackbars

suspend fun sendRetrySnackbar(
    label: String,
    action: () -> Unit
) {
    SnackbarController.sendEvent(
        SnackbarEvent(
            message = label,
            action = SnackbarAction(
                name = "Retry",
                action = action
            )
        )
    )
}