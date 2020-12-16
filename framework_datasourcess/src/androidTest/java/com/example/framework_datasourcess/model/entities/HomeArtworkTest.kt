package com.example.framework_datasourcess.model.entities

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.local.dao.HomeArtworkDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.artwork.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeArtworkTest{
    private lateinit var dataBase: ArtsyDataBase
    private lateinit var artworkDao:ArtworkDao
    private lateinit var homeArtworkDao:HomeArtworkDao

    private val dummyArtworkData = listOf<Artwork>(
            Artwork("adfcsdv", "fwv", false, false, true, "none", "fwvvwer", "vde", "dvkjsnvdjknsfdbs", "sjdvnifvn", Dimensions(null), "vkflvksf", 2.5, "1", null, null, Links(
                    Artists(""), CollectionUsers(""), Genes(""), Image(""), Partner(""), Permalink(""), SaleArtworks(""), Self(""), SimilarArtworks(""), Thumbnail("")
            ), "ndvknsf", "asdvsfd", "avwlvs", true, "dvkldskv", "lvslkdv", "lnsjkdns", false, "Hello", true, "adsvsdv", null),
            Artwork("adfcsdv", "fwv", false, false, true, "none", "fwvvwer", "vde", "dvkjsnvdjknsfdbs", "sjdvnifvn", Dimensions(null), "vkflvksf", 2.5, "2", null, null, Links(
                    Artists(""), CollectionUsers(""), Genes(""), Image(""), Partner(""), Permalink(""), SaleArtworks(""), Self(""), SimilarArtworks(""), Thumbnail("")
            ), "ndvknsf", "asdvsfd", "avwlvs", true, "dvkldskv", "lvslkdv", "lnsjkdns", false, "SecondTitle", true, "adsvsdv", null),
            Artwork("adfcsdv", "fwv", false, false, true, "none", "fwvvwer", "vde", "dvkjsnvdjknsfdbs", "sjdvnifvn", Dimensions(null), "vkflvksf", 2.5, "3", null, null, Links(
                    Artists(""), CollectionUsers(""), Genes(""), Image(""), Partner(""), Permalink(""), SaleArtworks(""), Self(""), SimilarArtworks(""), Thumbnail("")
            ), "ndvknsf", "asdvsfd", "avwlvs", true, "dvkldskv", "lvslkdv", "lnsjkdns", false, "Title", true, "adsvsdv", null),
    )


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        dataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                ArtsyDataBase::class.java)
                .allowMainThreadQueries()
                .build()
        artworkDao = dataBase.getArtworksDao()
        homeArtworkDao = dataBase.getHomeArtworkDao()
    }

    @Test
    fun justTest(){
        runBlocking {
            dataBase.runInTransaction {
                artworkDao.insertAll(dummyArtworkData)
                val homeData = dummyArtworkData.map {
                    HomeArtwork(it.id)
                }
                homeArtworkDao.insertAll(homeData)
            }
        }

        val res = homeArtworkDao.getAllArtworks()
        assertThat(res.size, CoreMatchers.equalTo(3))

        artworkDao.deleteById(dummyArtworkData[0].id)

        val res2 = homeArtworkDao.getAllArtworks()
        assertThat(res2.size, CoreMatchers.equalTo(2))
    }

    @After
    fun tearDown() {
        dataBase.close()
    }
}