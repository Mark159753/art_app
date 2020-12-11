package com.example.framework_datasourcess.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "artsyRemoteKeys")
data class ArtsyRemoteKeys(
    val itemType:Int,
    val itemId:String,
    val prevKey: Int?,
    val nextKey: Int?,
    @PrimaryKey(autoGenerate = true)
    var primaryKey:Long? = null
):Parcelable{

    companion object{
        const val ARTIST_TYPE = 1
        const val ARTWORK_TYPE = 2
    }
}