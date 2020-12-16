package com.example.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtworkLinks(
    val artists: String?,
    val collectionUsers: String?,
    val genes: String?,
    val image: String?,
    val partner: String?,
    val permalink: String?,
    val saleArtworks: String?,
    val self: String?,
    val similarArtworks: String?,
    val thumbnail: String?
):Parcelable