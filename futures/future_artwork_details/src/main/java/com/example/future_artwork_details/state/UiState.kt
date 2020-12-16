package com.example.future_artwork_details.state

sealed class UiState {
    object Loading:UiState()
    data class Error(val e:Throwable):UiState()
    object NoInternetConnection:UiState()
    object Success:UiState()
}