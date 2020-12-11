package com.example.search_component.di

import com.example.search_component.repository.SearchRepository
import com.example.search_component.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface SearchRepositoryModule {

    @Binds
    fun bindsSearchRepository(repositoryImpl: SearchRepositoryImpl):SearchRepository
}