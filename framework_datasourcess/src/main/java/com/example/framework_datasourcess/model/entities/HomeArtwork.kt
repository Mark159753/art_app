package com.example.framework_datasourcess.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.framework_datasourcess.model.artwork.Artwork

@Entity(tableName = "homeArtwork", foreignKeys = [
    ForeignKey(entity = Artwork::class,
    parentColumns = ["id"],
    childColumns = ["artworkId"],
    onDelete = ForeignKey.NO_ACTION,
    deferred = true)
])
data class HomeArtwork(
        val artworkId:String,
        @PrimaryKey(autoGenerate = true)
        val id:Long? = null
)