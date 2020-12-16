package com.example.framework_datasourcess.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.framework_datasourcess.model.entities.HomeArtwork
import com.example.framework_datasourcess.model.artwork.Artwork

@Dao
interface HomeArtworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:List<HomeArtwork>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: HomeArtwork)

    @Query("SELECT artwork.* FROM artwork INNER JOIN homeArtwork ON homeArtwork.artworkId = artwork.id")
    fun getAllArtworks(): List<Artwork>

    @Query("SELECT artwork.* FROM artwork INNER JOIN homeArtwork ON homeArtwork.artworkId = artwork.id")
    fun getAllArtworksPagingFactory(): PagingSource<Int, Artwork>

    @Query("DELETE FROM homeArtwork")
    fun deleteAllArtworks()
}