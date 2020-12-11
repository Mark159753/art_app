package com.example.home_component.di

import androidx.paging.PagingData
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.home_component.domain.interactor.ArtistUseCase
import com.example.home_component.domain.interactor.ArtworkUseCase
import com.example.home_component.domain.interactor.UseCase
import com.example.home_component.domain.repository.HomeArtRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Flowable

@Module
object HomeUseCaseModule {

    @Provides
    fun provideArtistUseCase(repo: HomeArtRepository): UseCase<Flowable<PagingData<ArtistModel>>>{
        return ArtistUseCase(repo)
    }



    @Provides
    fun provideArtworkUseCase(repo: HomeArtRepository): UseCase<Flowable<PagingData<ArtworkModel>>> {
        return ArtworkUseCase(repo)
    }
}