package com.example.framework_datasourcess.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.entities.HomeArtist

@Dao
interface HomeArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:List<HomeArtist>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: HomeArtist)

    @Query("SELECT artist.* FROM artist INNER JOIN homeArtist ON homeArtist.artistId = artist.id")
    fun getAllArtist(): List<Artist>

    @Query("SELECT artist.* FROM artist INNER JOIN homeArtist ON homeArtist.artistId = artist.id")
    fun getPagingSource(): PagingSource<Int, Artist>

    @Query("DELETE FROM homeArtist")
    fun deleteAllArtist()
}