package com.example.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistLinks(
    val artworks: String?,
    val genes: String?,
    val image: String?,
    val permalink: String?,
    val publishedArtworks: String?,
    val self: String?,
    val similarArtists: String?,
    val similarContemporaryArtists: String?,
    val thumbnail: String?
):Parcelable