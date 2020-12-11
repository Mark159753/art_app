package com.example.home_component.data.datasources.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.artist.*
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LocalArtistDataSourceTest{

    private lateinit var database:ArtsyDataBase
    private lateinit var artistDao:ArtistDao
    private lateinit var localArtistDataSource: LocalArtistDataSource

    @Before
    fun init(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, ArtsyDataBase::class.java)
                .allowMainThreadQueries()
                .build()
        artistDao = database.getArtistDao()
        localArtistDataSource = LocalArtistDataSource(artistDao)
    }


    @Test
    fun firstTest(){
        val artist = Artist("null", "null", "null", "0", "male", "here", "1111", listOf("", "", ""),
        Links(Artworks(""), Genes(""), Image(""), Permalink(""), PublishedArtworks(""), Self(""), SimilarArtists(""), SimilarContemporaryArtists(""), Thumbnail("")),
        "unknown", "Maria", "poland", "", "-", "null")
        artist.primaryKey = 1L
        artistDao.insert(artist)
        val res = artistDao.getAllArtist()
        assertThat(res, CoreMatchers.hasItem(artist))
    }

    @After
    fun close(){
        database.close()
    }
}