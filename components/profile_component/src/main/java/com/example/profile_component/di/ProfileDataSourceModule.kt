package com.example.profile_component.di

import com.example.profile_component.datasources.abstraction.ProfileLocalDataSource
import com.example.profile_component.datasources.abstraction.ProfileRemoteDataSource
import com.example.profile_component.datasources.local.ProfileLocalDataSourceImpl
import com.example.profile_component.datasources.remote.ProfileRemoteDataSourceImpl
import com.example.profile_component.repository.UserProfileRepository
import com.example.profile_component.repository.UserProfileRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ProfileDataSourceModule {

    @Binds
    abstract fun bindsLocaleDataSource(source:ProfileLocalDataSourceImpl):ProfileLocalDataSource

    @Binds
    abstract fun bindsRemoteDataSource(source:ProfileRemoteDataSourceImpl):ProfileRemoteDataSource

    @Binds
    abstract fun bindsProfileRepository(repository:UserProfileRepositoryImpl):UserProfileRepository
}