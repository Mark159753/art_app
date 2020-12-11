package com.example.framework_datasourcess.model.artwork


import com.google.gson.annotations.SerializedName

data class Embedded(
    @SerializedName("artworks")
    val artworks: List<Artwork>
)