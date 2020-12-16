package com.example.framework_datasourcess.model.artist


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

@Entity(tableName = "artist")
data class Artist @Inject constructor (
    @SerializedName("biography")
    val biography: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("deathday")
    val deathday: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("hometown")
    val hometown: String?,
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("image_versions")
    val imageVersions: List<String>?,
    @Embedded(prefix = "links_")
    @SerializedName("_links")
    val links: Links,
    @SerializedName("location")
    val location: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("sortable_name")
    val sortableName: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
){
//    @PrimaryKey(autoGenerate = true)
//    var primaryKey:Long? = null
}