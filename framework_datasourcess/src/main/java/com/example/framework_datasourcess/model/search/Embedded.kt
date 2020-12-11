package com.example.framework_datasourcess.model.search


import com.google.gson.annotations.SerializedName

data class Embedded(
    @SerializedName("results")
    val results: List<Result>?
)