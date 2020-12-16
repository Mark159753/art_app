package com.example.home_component.data.datasources.local

import androidx.paging.PagingSource
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.local.dao.HomeArtistDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.entities.HomeArtist
import com.example.home_component.data.datasources.abstraction.LocalDataSource
import javax.inject.Inject

class LocalArtistDataSource @Inject constructor(
    private val artistDao: ArtistDao,
    private val homeArtistDao: HomeArtistDao,
    private val db:ArtsyDataBase
):LocalDataSource<Artist> {

    override fun insertAllItems(list: List<Artist>) {
        db.runInTransaction {
            artistDao.insertAll(list)
            homeArtistDao.insertAll(list.map { HomeArtist(it.id) })
        }
    }

    override fun insertItem(item: Artist) {
        db.runInTransaction {
            artistDao.insert(item)
            homeArtistDao.insert(HomeArtist(item.id))
        }
    }

    override fun getAllItem(): List<Artist> = homeArtistDao.getAllArtist()

    override fun getItemsPagingSource(): PagingSource<Int, Artist> = homeArtistDao.getPagingSource()

    override fun deleteAllItems() {
        homeArtistDao.deleteAllArtist()
    }
}