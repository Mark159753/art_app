package com.example.framework_datasourcess.model.artist


import com.google.gson.annotations.SerializedName

data class Embedded(
    @SerializedName("artists")
    val artists: List<Artist>
)