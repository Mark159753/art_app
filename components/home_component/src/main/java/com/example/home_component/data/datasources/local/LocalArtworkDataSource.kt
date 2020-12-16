package com.example.home_component.data.datasources.local

import androidx.paging.PagingSource
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.local.dao.HomeArtworkDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.framework_datasourcess.model.entities.HomeArtwork
import com.example.home_component.data.datasources.abstraction.LocalDataSource
import javax.inject.Inject


class LocalArtworkDataSource @Inject constructor(
    private val artworkDao: ArtworkDao,
    private val homeArtworkDao: HomeArtworkDao,
    private val db:ArtsyDataBase
):LocalDataSource<Artwork> {

    override fun insertAllItems(list: List<Artwork>) {
        db.runInTransaction {
            artworkDao.insertAll(list)
            homeArtworkDao.insertAll(list.map { HomeArtwork(it.id) })
        }
    }

    override fun insertItem(item: Artwork) {
        db.runInTransaction {
            artworkDao.insert(item)
            homeArtworkDao.insert(HomeArtwork(item.id))
        }
    }

    override fun getAllItem(): List<Artwork> = homeArtworkDao.getAllArtworks()

    override fun getItemsPagingSource(): PagingSource<Int, Artwork> = homeArtworkDao.getAllArtworksPagingFactory()

    override fun deleteAllItems() {
        homeArtworkDao.deleteAllArtworks()
    }
}