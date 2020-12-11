package com.example.framework_datasourcess.model.artwork


import com.google.gson.annotations.SerializedName

data class In(
    @SerializedName("depth")
    val depth: Double?,
    @SerializedName("diameter")
    val diameter: Double?,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("width")
    val width: Double?
)