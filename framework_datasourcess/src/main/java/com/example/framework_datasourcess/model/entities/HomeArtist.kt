package com.example.framework_datasourcess.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.framework_datasourcess.model.artist.Artist

@Entity(tableName = "homeArtist", foreignKeys = [
    ForeignKey(entity = Artist::class,
    parentColumns = ["id"],
    childColumns = ["artistId"],
    onDelete = ForeignKey.NO_ACTION,
    deferred = true)
])
data class HomeArtist(
        val artistId:String,
        @PrimaryKey(autoGenerate = true)
        val id:Long? = null
)