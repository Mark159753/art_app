package com.example.artapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ViewModelFactoryDI
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryDI): ViewModelProvider.Factory
}