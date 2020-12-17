package com.example.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistModel(
    val biography: String?,
    val birthday: String?,
    val createdAt: String?,
    val deathday: String?,
    val gender: String?,
    val hometown: String?,
    val id: String,
    val imageVersions: List<String>?,
    val links: ArtistLinks,
    val location: String?,
    val name: String?,
    val nationality: String?,
    val slug: String?,
    val sortableName: String?,
    val updatedAt: String?
):Parcelable