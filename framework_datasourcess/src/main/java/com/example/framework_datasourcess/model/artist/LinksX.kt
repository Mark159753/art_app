package com.example.framework_datasourcess.model.artist


import com.google.gson.annotations.SerializedName

data class LinksX(
    @SerializedName("next")
    val next: Next?,
    @SerializedName("self")
    val self: SelfX?
)