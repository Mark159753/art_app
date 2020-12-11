package com.example.framework_datasourcess.model.artwork


import com.google.gson.annotations.SerializedName

data class Permalink(
    @SerializedName("href")
    val href: String?
)