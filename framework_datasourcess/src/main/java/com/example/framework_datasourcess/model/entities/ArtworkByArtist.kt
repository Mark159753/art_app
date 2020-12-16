package com.example.framework_datasourcess.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artwork.Artwork

@Entity(tableName = "artworkByArtist", foreignKeys = [
    ForeignKey(entity = Artist::class,
        parentColumns = ["id"],
        childColumns = ["artistId"],
        onDelete = ForeignKey.NO_ACTION,
        deferred = true),
    ForeignKey(entity = Artwork::class,
        parentColumns = ["id"],
        childColumns = ["artworkId"],
        onDelete = ForeignKey.NO_ACTION,
        deferred = true)
])
data class ArtworkByArtist(
    val artistId:String,
    val artworkId:String,
    @PrimaryKey(autoGenerate = true)
    val id:Long? = null
)