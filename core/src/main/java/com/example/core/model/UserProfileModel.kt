package com.example.core.model

data class UserProfileModel(
    val authenticationToken: String?,
    val createdAt: String?,
    val email: String?,
    val gender: String?,
    val id: String,
    val name: String?,
    val paddleNumber: String?,
    val phone: String?,
    val pin: String?,
    val receiveSms: Boolean?,
    val resetPasswordToken: String?,
    val timezone: String?,
    val timezoneCode: String?,
    val type: String?,
    val updatedAt: String?
)