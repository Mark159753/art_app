package com.example.framework_datasourcess.di

import android.content.Context
import androidx.room.Room
import com.example.framework_datasourcess.DATABASE_NAME
import com.example.framework_datasourcess.local.dao.AccessTokenDao
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context:Context):ArtsyDataBase{
        return Room.databaseBuilder(context, ArtsyDataBase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    fun provideAccessTokenDao(db:ArtsyDataBase):AccessTokenDao{
        return db.getAccessTokenDao()
    }

    @Provides
    fun provideArtistDao(db:ArtsyDataBase): ArtistDao{
        return db.getArtistDao()
    }

    @Provides
    fun provideArtworkDao(db: ArtsyDataBase): ArtworkDao{
        return db.getArtworksDao()
    }
}