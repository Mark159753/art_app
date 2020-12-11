package com.example.framework_datasourcess.model.artist


import com.google.gson.annotations.SerializedName

data class PublishedArtworks(
    @SerializedName("href")
    val href: String?
)