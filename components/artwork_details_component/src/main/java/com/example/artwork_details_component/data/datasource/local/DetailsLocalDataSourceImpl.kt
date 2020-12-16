package com.example.artwork_details_component.data.datasource.local

import androidx.paging.PagingSource
import com.example.artwork_details_component.data.datasource.abstraction.DetailsLocalDataSource
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.local.dao.ArtworkByArtistDao
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.framework_datasourcess.model.entities.ArtworkByArtist
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DetailsLocalDataSourceImpl @Inject constructor(
    private val artistDao: ArtistDao,
    private val artworkDao: ArtworkDao,
    private val artworkByArtistDao: ArtworkByArtistDao,
    private val db:ArtsyDataBase
) :DetailsLocalDataSource<Artwork>{

    override fun insertAllItems(artistId:String, list: List<Artwork>) {
        db.runInTransaction {
            artworkDao.insertAll(list)
            artworkByArtistDao.insertAll(list.map { ArtworkByArtist(artistId, it.id) })
        }
    }

    override fun insertItem(artistId:String, item: Artwork) {
        db.runInTransaction {
            artworkDao.insert(item)
            artworkByArtistDao.insert(ArtworkByArtist(artistId, item.id))
        }
    }

    override fun insertArtistAndGetArtist(artist: Artist):Single<Artist> {
        artistDao.insert(artist)
        return artistDao.getArtistByIdSingle(artist.id)
    }

    override fun insertArtist(artist: Artist) {
        artistDao.insert(artist)
    }

    override fun getArtistById(id: String): Artist {
        return artistDao.getArtistById(id)
    }

    override fun getAllItemById(id:String): List<Artwork> {
        return artworkByArtistDao.getAllArtworkById(id)
    }

    override fun getItemsPagingSourceById(id: String): PagingSource<Int, Artwork> {
        return artworkByArtistDao.getPagingSourceById(id)
    }

    override fun deleteAllItems() {
        artworkByArtistDao.deleteAllArtwork()
    }

    override fun getArtistByArtworkId(id: String): Single<Artist> {
        return artworkByArtistDao.getArtistByArtworkId(id)
    }

    override fun deleteById(id: String) {
        artworkByArtistDao.deleteById(id)
    }
}