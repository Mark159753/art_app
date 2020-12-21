package com.example.framework_datasourcess.model.user


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("href")
    val href: String?
)