package com.example.framework_datasourcess.model

data class DeleteAccessToken(
    val expired_at: String,
    val id: String,
    val token: String,
    val type: String
)