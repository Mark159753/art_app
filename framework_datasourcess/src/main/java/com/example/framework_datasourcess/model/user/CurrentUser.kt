package com.example.framework_datasourcess.model.user


import com.google.gson.annotations.SerializedName

data class CurrentUser(
    @SerializedName("id")
    val id: String?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("name")
    val name: String?
)