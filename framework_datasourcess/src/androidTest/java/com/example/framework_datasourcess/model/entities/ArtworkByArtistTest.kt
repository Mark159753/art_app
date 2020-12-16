package com.example.framework_datasourcess.model.entities

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.local.dao.ArtworkByArtistDao
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.artist.*
import com.example.framework_datasourcess.model.artwork.*
import com.example.framework_datasourcess.model.artwork.Genes
import com.example.framework_datasourcess.model.artwork.Image
import com.example.framework_datasourcess.model.artwork.Links
import com.example.framework_datasourcess.model.artwork.Permalink
import com.example.framework_datasourcess.model.artwork.Self
import com.example.framework_datasourcess.model.artwork.Thumbnail
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtworkByArtistTest {
    private lateinit var dataBase: ArtsyDataBase
    private lateinit var artworkDao: ArtworkDao
    private lateinit var artworkByArtist: ArtworkByArtistDao
    private lateinit var artistDao: ArtistDao

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

    private val dummyArtistData = Artist("fsaegd", "vabff", "afasdv", "advsb", "adfvvds", "sadvafsvs", "1", null,
    com.example.framework_datasourcess.model.artist.Links(Artworks(""), com.example.framework_datasourcess.model.artist.Genes(""), com.example.framework_datasourcess.model.artist.Image(""), com.example.framework_datasourcess.model.artist.Permalink(""), PublishedArtworks(""), com.example.framework_datasourcess.model.artist.Self(""), SimilarArtists(""), SimilarContemporaryArtists(""), com.example.framework_datasourcess.model.artist.Thumbnail("")),
    "dsavdssd", "vasvs", "asdvsb", "sadvsfb", "sadvsb", "adsvsbf")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        dataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            ArtsyDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        artworkDao = dataBase.getArtworksDao()
        artworkByArtist = dataBase.getArtworkByArtistDao()
        artistDao = dataBase.getArtistDao()
    }

    @Test
    fun justTest(){
        dataBase.runInTransaction {
            artistDao.insert(dummyArtistData)
            artworkDao.insertAll(dummyArtworkData)
            artworkByArtist.insertAll(dummyArtworkData.map { ArtworkByArtist(dummyArtistData.id, it.id) })
        }

        val res1 = artworkByArtist.getAllArtworkById(dummyArtistData.id)
        ViewMatchers.assertThat(res1.size, CoreMatchers.equalTo(3))
        ViewMatchers.assertThat(res1[0].id, CoreMatchers.equalTo(dummyArtworkData[0].id))

        artistDao.deleteAllArtist()
        val res2 = artworkByArtist.getAllArtworkById(dummyArtistData.id)
        ViewMatchers.assertThat(res2.size, CoreMatchers.equalTo(0))
    }

    @Test
    fun secondTest(){
        dataBase.runInTransaction {
            artistDao.insert(dummyArtistData)
            artworkDao.insertAll(dummyArtworkData)
            artworkByArtist.insertAll(dummyArtworkData.map { ArtworkByArtist(dummyArtistData.id, it.id) })
        }

        val res1 = artworkByArtist.getAllArtworkById(dummyArtistData.id)
        ViewMatchers.assertThat(res1.size, CoreMatchers.equalTo(3))

        artworkDao.deleteById(dummyArtworkData[0].id)

        val res2 = artworkByArtist.getAllArtworkById(dummyArtistData.id)
        ViewMatchers.assertThat(res2.size, CoreMatchers.equalTo(2))
    }


    @After
    fun tearDown() {
        dataBase.close()
    }
}