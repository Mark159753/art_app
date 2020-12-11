package com.example.framework_datasourcess.model.artist


import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("_embedded")
    val embedded: Embedded,
    @SerializedName("_links")
    val links: LinksX
)