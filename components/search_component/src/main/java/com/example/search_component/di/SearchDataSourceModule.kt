package com.example.search_component.di

import com.example.search_component.datasource.SearchRemoteDataSource
import com.example.search_component.datasource.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface SearchDataSourceModule {

    @Binds
    fun bindSearchRemoteDataSource(source:SearchRemoteDataSourceImpl):SearchRemoteDataSource
}