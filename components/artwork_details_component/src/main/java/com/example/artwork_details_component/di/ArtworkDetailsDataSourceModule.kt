package com.example.artwork_details_component.di

import com.example.artwork_details_component.data.datasource.abstraction.DetailsLocalDataSource
import com.example.artwork_details_component.data.datasource.abstraction.DetailsRemoteDataSource
import com.example.artwork_details_component.data.datasource.local.DetailsLocalDataSourceImpl
import com.example.artwork_details_component.data.datasource.remote.DetailsRemoteArtistDataSource
import com.example.artwork_details_component.data.repository.ArtworkDetailsRepositoryImpl
import com.example.artwork_details_component.domain.repository.ArtworkDetailsRepository
import com.example.core.di.DetailsArtworkScope
import com.example.framework_datasourcess.model.artwork.Artwork
import dagger.Binds
import dagger.Module

@Module
abstract class ArtworkDetailsDataSourceModule {

    @Binds
    abstract fun bindDetailsRemoteDataSource(remote: DetailsRemoteArtistDataSource): DetailsRemoteDataSource

    @Binds
    abstract fun bindDetailsLocalDataSource(local: DetailsLocalDataSourceImpl): DetailsLocalDataSource<Artwork>

    @Binds
    @DetailsArtworkScope
    abstract fun bindArtworkDetailsRepository(repository: ArtworkDetailsRepositoryImpl):ArtworkDetailsRepository
}