package com.example.artwork_details_component.di

import com.example.artwork_details_component.domain.interactor.ArtworkByArtistUseCase
import com.example.artwork_details_component.domain.interactor.ArtistByArtworkIdUseCase
import com.example.artwork_details_component.domain.repository.ArtworkDetailsRepository
import com.example.core.di.DetailsArtworkScope
import dagger.Module
import dagger.Provides

@Module
object ArtworkDetailsUseCaseModule {

    @Provides
    @DetailsArtworkScope
    fun provideArtworkByArtistUseCase(
            repository: ArtworkDetailsRepository
    ): ArtworkByArtistUseCase{
        return ArtworkByArtistUseCase(repository)
    }

    @Provides
    @DetailsArtworkScope
    fun provideArtworkByArtworkIdUseCase(
            repository: ArtworkDetailsRepository
    ): ArtistByArtworkIdUseCase {
        return ArtistByArtworkIdUseCase(repository)
    }
}