package com.example.home_component.di

import androidx.paging.ExperimentalPagingApi
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.local.dao.ArtistDao
import com.example.framework_datasourcess.local.dao.ArtworkDao
import com.example.framework_datasourcess.local.dao.HomeArtistDao
import com.example.framework_datasourcess.local.dao.HomeArtworkDao
import com.example.framework_datasourcess.local.db.ArtsyDataBase
import com.example.framework_datasourcess.model.artist.Artist
import com.example.framework_datasourcess.model.artist.ArtistResponse
import com.example.framework_datasourcess.model.artwork.Artwork
import com.example.framework_datasourcess.model.artwork.ArtworkRes
import com.example.framework_datasourcess.remote.main.ArtsyService
import com.example.home_component.data.datasources.abstraction.LocalDataSource
import com.example.home_component.data.datasources.abstraction.RemoteDataSource
import com.example.home_component.data.datasources.local.LocalArtistDataSource
import com.example.home_component.data.datasources.local.LocalArtworkDataSource
import com.example.home_component.data.datasources.paging.ArtistRxRemoteMediator
import com.example.home_component.data.datasources.paging.ArtworkRxRemoteMediator
import com.example.home_component.data.datasources.remote.RemoteArtistDataSource
import com.example.home_component.data.datasources.remote.RemoteArtworksDataSource
import dagger.Module
import dagger.Provides

@Module
object DataSourcesModule {

    @Provides
    fun provideLocalArtistDataSource(dao: ArtistDao, homeArtistDao: HomeArtistDao, db:ArtsyDataBase): LocalDataSource<Artist> {
        return LocalArtistDataSource(dao, homeArtistDao, db)
    }

    @Provides
    fun provideLocalArtworkDataSource(artworkDao: ArtworkDao, homeArtworkDao: HomeArtworkDao, db:ArtsyDataBase):LocalDataSource<Artwork>{
        return LocalArtworkDataSource(artworkDao, homeArtworkDao, db)
    }

    @Provides
    fun provideRemoteArtistDataSource(apiService: ArtsyService): RemoteDataSource<ArtistResponse>{
        return RemoteArtistDataSource(apiService)
    }

    @Provides
    fun provideRemoteArtworksDataSource(apiService: ArtsyService):RemoteDataSource<ArtworkRes>{
        return RemoteArtworksDataSource(apiService)
    }

    @ExperimentalPagingApi
    @Provides
    fun provideArtistRxRemoteMediator(
        remote: RemoteDataSource<ArtistResponse>,
        schedulerProvider: SchedulerProvider,
        dataBase: ArtsyDataBase,
        local:LocalDataSource<Artist>
    ): ArtistRxRemoteMediator{
        return ArtistRxRemoteMediator(remote, schedulerProvider,
        dataBase, local)
    }

    @ExperimentalPagingApi
    @Provides
    fun provideArtworkRxRemoteMediator(
        remote: RemoteDataSource<ArtworkRes>,
        schedulerProvider: SchedulerProvider,
        dataBase: ArtsyDataBase,
        local: LocalDataSource<Artwork>): ArtworkRxRemoteMediator{
        return ArtworkRxRemoteMediator(remote, schedulerProvider, dataBase, local)
    }

}