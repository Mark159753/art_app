package com.example.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtworkDimensions(
    val depth: Double?,
    val diameter: Double?,
    val height: Double?,
    val text: String?,
    val width: Double?
):Parcelable