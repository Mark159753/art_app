package com.example.framework_datasourcess.di

import android.content.Context
import androidx.room.Room
import com.example.framework_datasourcess.DATABASE_NAME
import com.example.framework_datasourcess.local.dao.*
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
                .fallbackToDestructiveMigration() //Remove this in Future
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

    @Provides
    fun provideHomeArtworkDao(db: ArtsyDataBase):HomeArtworkDao{
        return db.getHomeArtworkDao()
    }

    @Provides
    fun provideHomeArtistDao(db: ArtsyDataBase):HomeArtistDao{
        return db.getHomeArtistDao()
    }

    @Provides
    fun provideArtworkByArtistDao(db: ArtsyDataBase):ArtworkByArtistDao{
        return db.getArtworkByArtistDao()
    }

    @Provides
    fun provideUserDetailsDao(db: ArtsyDataBase):UserDetailsDao{
        return db.getUserDetailsDao()
    }
}