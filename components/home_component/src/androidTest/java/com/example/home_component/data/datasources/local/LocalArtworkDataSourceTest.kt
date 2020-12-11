package com.example.home_component.data.datasources.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.framework_datasourcess.local.dao.ArtsyRemoteKeysDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.ArtsyRemoteKeys
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.framework_datasourcess.model.artwork.Dimensions
import com.example.framework_datasourcess.model.artwork.Links
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LocalArtworkDataSourceTest{

    private lateinit var database:ArtsyDataBase
    private lateinit var localDataSource:LocalArtworkDataSource
    private lateinit var remoteKeysDao: ArtsyRemoteKeysDao

    @Before
    fun initDB(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, ArtsyDataBase::class.java)
                .build()
        localDataSource = LocalArtworkDataSource(database.getArtworksDao())
        remoteKeysDao = database.getArtsyRemoteKeysDao()
    }


    @Test
    fun remoteKeysDaoTest(){
        val remoteKey = arrayListOf(ArtsyRemoteKeys(ArtsyRemoteKeys.ARTWORK_TYPE, "1L", 0, 20))
        remoteKeysDao.insert(remoteKey)
        val res = remoteKeysDao.getRemotesKeysByItemId("1L", ArtsyRemoteKeys.ARTWORK_TYPE)
        assertThat(res, `is`(equalTo(remoteKey[0])))
    }

    @Test
    fun localDataSource(){
        val item = Artwork(null, null, null, null, null, "bad0", "no", null,
        null, null, Dimensions(null), null, null, "1", null, null, Links(null, null, null, null, null, null, null, null, null, null),
        null, null, null, null, null, null, null, null, null, null, null, null)

//        localDataSource.insertItem(item)
//        val res = localDataSource.getAllItem()
        val dao = database.getArtworksDao()
        dao.insert(item)
        val res = dao.getAllArtworks()
        assertThat(res, CoreMatchers.notNullValue())
        assertThat(res, CoreMatchers.hasItem(item))
    }

    @After
    fun closeDB(){
        database.close()
    }
}