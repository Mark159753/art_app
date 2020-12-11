package com.example.framework_datasourcess.model.search


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("permalink")
    val permalink: Permalink?,
    @SerializedName("self")
    val self: Self?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?
)