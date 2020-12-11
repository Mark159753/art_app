package com.example.framework_datasourcess.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framework_datasourcess.model.artist.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:List<Artist>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item:Artist)

    @Query("SELECT * FROM artist")
    fun getAllArtist(): List<Artist>

    @Query("SELECT * FROM artist")
    fun getPagingSource():PagingSource<Int, Artist>

    @Query("DELETE FROM artist")
    fun deleteAllArtist()
}