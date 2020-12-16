package com.example.framework_datasourcess.model.artwork


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "artwork")
data class Artwork(
    @SerializedName("additional_information")
    val additionalInformation: String?,
    @SerializedName("blurb")
    val blurb: String?,
    @SerializedName("can_acquire")
    val canAcquire: Boolean?,
    @SerializedName("can_inquire")
    val canInquire: Boolean?,
    @SerializedName("can_share")
    val canShare: Boolean?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("collecting_institution")
    val collectingInstitution: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("cultural_maker")
    val culturalMaker: String?,
    @SerializedName("date")
    val date: String?,
    @Embedded(prefix = "dimensions_")
    @SerializedName("dimensions")
    val dimensions: Dimensions?,
    @SerializedName("exhibition_history")
    val exhibitionHistory: String?,
    @SerializedName("iconicity")
    val iconicity: Double?,
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("image_rights")
    val imageRights: String?,
    @SerializedName("image_versions")
    val imageVersions: List<String>?,
    @Embedded(prefix = "links_")
    @SerializedName("_links")
    val links: Links,
    @SerializedName("literature")
    val literature: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("provenance")
    val provenance: String?,
    @SerializedName("published")
    val published: Boolean?,
    @SerializedName("sale_message")
    val saleMessage: String?,
    @SerializedName("signature")
    val signature: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("sold")
    val sold: Boolean?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("unique")
    val unique: Boolean?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("website")
    val website: String?
){
//    @PrimaryKey(autoGenerate = true)
//    var primaryKey:Long? = null
}