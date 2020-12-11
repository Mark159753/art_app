package com.example.future_authorization.state

import com.example.framework_datasourcess.model.AccessToken


data class UiState(
        val isLoading:Boolean = false,
        val token:AccessToken? = null,
        val error:Throwable? = null,
){

    companion object {
        fun loading(): UiState = UiState(true)

        fun error(e: Throwable): UiState = UiState(error = e)

        fun success(t: AccessToken): UiState = UiState(token = t)
    }
}