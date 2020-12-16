package com.example.framework_datasourcess.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framework_datasourcess.model.artwork.Artwork

@Dao
interface ArtworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:List<Artwork>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Artwork)

    @Query("SELECT * FROM artwork")
    fun getAllArtworks(): List<Artwork>

    @Query("SELECT * FROM artwork")
    fun getPagingSource(): PagingSource<Int, Artwork>

    @Query("DELETE FROM artwork")
    fun deleteAllArtworks()

    @Query("DELETE FROM artwork WHERE id = :id")
    fun deleteById(id:String)
}