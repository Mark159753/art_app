package com.example.framework_datasourcess.model.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("_embedded")
    val embedded: Embedded?,
    @SerializedName("_links")
    val links: LinksX?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("q")
    val q: String?,
    @SerializedName("total_count")
    val totalCount: Int?
)