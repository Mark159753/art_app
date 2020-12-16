package com.example.framework_datasourcess.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framework_datasourcess.local.converters.ListStringConverter
import com.example.framework_datasourcess.local.dao.*
import com.example.framework_datasourcess.model.AccessToken
import com.example.framework_datasourcess.model.ArtsyRemoteKeys
import com.example.framework_datasourcess.model.entities.HomeArtwork
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.framework_datasourcess.model.entities.ArtworkByArtist
import com.example.framework_datasourcess.model.entities.HomeArtist

@Database(
    entities = [
        AccessToken::class,
        Artist::class,
        Artwork::class,
        ArtsyRemoteKeys::class,
        HomeArtwork::class,
        HomeArtist::class,
        ArtworkByArtist::class
    ],
    version = 5, exportSchema = false)
@TypeConverters(ListStringConverter::class)
abstract class ArtsyDataBase:RoomDatabase() {

    abstract fun getAccessTokenDao():AccessTokenDao

    abstract fun getArtistDao():ArtistDao

    abstract fun getArtworksDao():ArtworkDao

    abstract fun getArtsyRemoteKeysDao():ArtsyRemoteKeysDao

    abstract fun getHomeArtworkDao():HomeArtworkDao

    abstract fun getHomeArtistDao():HomeArtistDao

    abstract fun getArtworkByArtistDao():ArtworkByArtistDao
}