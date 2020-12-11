package com.example.future_search.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.future_search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindHomeViewModel(model: SearchViewModel): ViewModel
}