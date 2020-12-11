package com.example.home_component.data.datasources.local

import androidx.paging.PagingSource
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.home_component.data.datasources.abstraction.LocalDataSource
import javax.inject.Inject


class LocalArtworkDataSource @Inject constructor(
    private val artworkDao: ArtworkDao
):LocalDataSource<Artwork> {

    override fun insertAllItems(list: List<Artwork>) {
        artworkDao.insertAll(list)
    }

    override fun insertItem(item: Artwork) {
        artworkDao.insert(item)
    }

    override fun getAllItem(): List<Artwork> = artworkDao.getAllArtworks()

    override fun getItemsPagingSource(): PagingSource<Int, Artwork> = artworkDao.getPagingSource()

    override fun deleteAllItems() {
        artworkDao.deleteAllArtworks()
    }
}