package com.example.framework_datasourcess.model.artwork


import com.google.gson.annotations.SerializedName

data class SimilarArtworks(
    @SerializedName("href")
    val href: String?
)