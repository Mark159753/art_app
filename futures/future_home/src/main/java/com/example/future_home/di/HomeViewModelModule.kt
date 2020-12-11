package com.example.future_home.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.future_home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(model: HomeViewModel): ViewModel
}