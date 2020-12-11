package com.example.home_component.di

import androidx.paging.ExperimentalPagingApi
import com.example.home_component.data.repository.HomeArtRepositoryImpl
import com.example.home_component.domain.repository.HomeArtRepository
import dagger.Binds
import dagger.Module


@Module
interface HomeRepositoryModule {

    @ExperimentalPagingApi
    @Binds
    fun bindHomeRepository(repos:HomeArtRepositoryImpl):HomeArtRepository
}