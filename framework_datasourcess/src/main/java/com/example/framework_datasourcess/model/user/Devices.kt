package com.example.framework_datasourcess.model.user


import com.google.gson.annotations.SerializedName

data class Devices(
    @SerializedName("href")
    val href: String?,
    @SerializedName("templated")
    val templated: Boolean?
)