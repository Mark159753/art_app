package com.example.framework_datasourcess.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.framework_datasourcess.model.entities.ArtworkByArtist
import io.reactivex.Single

@Dao
interface ArtworkByArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:List<ArtworkByArtist>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ArtworkByArtist)

    @Query("SELECT artwork.* FROM artwork INNER JOIN artworkByArtist ON artworkByArtist.artworkId = artwork.id WHERE artworkByArtist.artistId = :id")
    fun getAllArtworkById(id:String): List<Artwork>

    @Query("SELECT artwork.* FROM artwork INNER JOIN artworkByArtist ON artworkByArtist.artworkId = artwork.id WHERE artworkByArtist.artistId = :id")
    fun getPagingSourceById(id:String): PagingSource<Int, Artwork>

    @Query("SELECT artist.* FROM artist INNER JOIN artworkByArtist ON artworkByArtist.artistId = artist.id WHERE artworkByArtist.artworkId = :id")
    fun getArtistByArtworkId(id:String):Single<Artist>

    @Query("DELETE FROM artworkByArtist")
    fun deleteAllArtwork()

    @Query("DELETE FROM artworkByArtist WHERE artistId = :id")
    fun deleteById(id: String)
}