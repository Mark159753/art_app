package com.example.framework_datasourcess.model.artwork


import com.google.gson.annotations.SerializedName

data class ArtworkRes(
    @SerializedName("_embedded")
    val embedded: Embedded,
    @SerializedName("_links")
    val links: LinksX
)