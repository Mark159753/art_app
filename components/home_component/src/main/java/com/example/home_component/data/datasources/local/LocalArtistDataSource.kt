package com.example.home_component.data.datasources.local

import androidx.paging.PagingSource
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.model.artist.Artist
import com.example.home_component.data.datasources.abstraction.LocalDataSource
import javax.inject.Inject

class LocalArtistDataSource @Inject constructor(
    private val artistDao: ArtistDao
):LocalDataSource<Artist> {

    override fun insertAllItems(list: List<Artist>) {
        artistDao.insertAll(list)
    }

    override fun insertItem(item: Artist) {
        artistDao.insert(item)
    }

    override fun getAllItem(): List<Artist> = artistDao.getAllArtist()

    override fun getItemsPagingSource(): PagingSource<Int, Artist> = artistDao.getPagingSource()

    override fun deleteAllItems() {
        artistDao.deleteAllArtist()
    }
}