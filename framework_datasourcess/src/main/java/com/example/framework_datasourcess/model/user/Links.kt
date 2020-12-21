package com.example.framework_datasourcess.model.user


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("public_collections")
    val publicCollections: PublicCollections?,
    @SerializedName("self")
    val self: Self?,
    @SerializedName("user_detail")
    val userDetail: UserDetail?
)