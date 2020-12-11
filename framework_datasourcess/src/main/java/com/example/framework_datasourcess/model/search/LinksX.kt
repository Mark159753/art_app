package com.example.framework_datasourcess.model.search


import com.google.gson.annotations.SerializedName

data class LinksX(
    @SerializedName("next")
    val next: Next?,
    @SerializedName("self")
    val self: SelfX?
)