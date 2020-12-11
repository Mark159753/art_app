package com.example.component_authorization.di

import com.example.component_authorization.data.datasorcess.abstraction.LocalAuthDataSource
import com.example.component_authorization.data.datasorcess.abstraction.RemoteAuthDataSource
import com.example.component_authorization.data.datasorcess.implementation.LocalAuthDataSourceImpl
import com.example.component_authorization.data.datasorcess.implementation.RemoteAuthDataSourceImpl
import com.example.component_authorization.data.repository.AuthRepositoryImpl
import com.example.component_authorization.domain.repository.AuthRepository
import com.example.core.di.AuthFragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourcesModule {

    @Binds
    abstract fun bindLocalAuthDataSource(dataSource: LocalAuthDataSourceImpl):LocalAuthDataSource

    @Binds
    abstract fun bindRemoteAuthDataSource(dataSource: RemoteAuthDataSourceImpl): RemoteAuthDataSource

    @AuthFragmentScope
    @Binds
    abstract fun bindAuthRepository(dataSource: AuthRepositoryImpl):AuthRepository
}