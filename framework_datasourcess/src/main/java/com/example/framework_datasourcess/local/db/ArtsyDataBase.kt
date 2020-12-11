package com.example.framework_datasourcess.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framework_datasourcess.local.converters.ListStringConverter
import com.example.framework_datasourcess.local.dao.AccessTokenDao
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.local.dao.ArtsyRemoteKeysDao
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.model.AccessToken
import com.example.framework_datasourcess.model.ArtsyRemoteKeys
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artwork.Artwork

@Database(
    entities = [
        AccessToken::class,
        Artist::class,
        Artwork::class,
        ArtsyRemoteKeys::class
    ],
    version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class)
abstract class ArtsyDataBase:RoomDatabase() {

    abstract fun getAccessTokenDao():AccessTokenDao

    abstract fun getArtistDao():ArtistDao

    abstract fun getArtworksDao():ArtworkDao

    abstract fun getArtsyRemoteKeysDao():ArtsyRemoteKeysDao
}