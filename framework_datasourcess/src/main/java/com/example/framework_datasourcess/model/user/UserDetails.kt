package com.example.framework_datasourcess.model.user


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userDetails")
data class UserDetails(
    @SerializedName("authentication_token")
    val authenticationToken: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("paddle_number")
    val paddleNumber: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("pin")
    val pin: String?,
    @SerializedName("receive_sms")
    val receiveSms: Boolean?,
    @SerializedName("reset_password_token")
    val resetPasswordToken: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_code")
    val timezoneCode: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)