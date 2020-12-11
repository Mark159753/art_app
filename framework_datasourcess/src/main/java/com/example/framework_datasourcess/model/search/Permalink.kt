package com.example.framework_datasourcess.model.search


import com.google.gson.annotations.SerializedName

data class Permalink(
    @SerializedName("href")
    val href: String?
)