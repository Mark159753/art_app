package com.example.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtworkModel (
    val additionalInformation: String?,
    val blurb: String?,
    val canAcquire: Boolean?,
    val canInquire: Boolean?,
    val canShare: Boolean?,
    val category: String?,
    val collectingInstitution: String?,
    val createdAt: String?,
    val culturalMaker: String?,
    val date: String?,
    val dimensions: ArtworkDimensions,
    val exhibitionHistory: String?,
    val iconicity: Double?,
    val id: String,
    val imageRights: String?,
    val imageVersions: List<String>?,
    val links: ArtworkLinks,
    val literature: String?,
    val medium: String?,
    val provenance: String?,
    val published: Boolean?,
    val saleMessage: String?,
    val signature: String?,
    val slug: String?,
    val sold: Boolean?,
    val title: String?,
    val unique: Boolean?,
    val updatedAt: String?,
    val website: String?
):Parcelable