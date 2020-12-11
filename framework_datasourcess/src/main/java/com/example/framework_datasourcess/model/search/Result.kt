package com.example.framework_datasourcess.model.search


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("description")
    val description: String?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("og_type")
    val ogType: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)