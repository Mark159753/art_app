package com.example.future_search.di

import com.example.core.di.SearchFragmentScope
import com.example.future_search.search.SearchFragment
import com.example.future_search.searchFilter.SearchFilter
import com.example.search_component.di.SearchDataSourceModule
import com.example.search_component.di.SearchRepositoryModule
import dagger.Subcomponent

@SearchFragmentScope
@Subcomponent(modules = [
    SearchDataSourceModule::class,
    SearchRepositoryModule::class,
    SearchViewModelModule::class
])
interface SearchFragmentComponent {

    fun inject(fragment:SearchFragment)

    fun inject(fragment:SearchFilter)

    @Subcomponent.Factory
    interface Factory{
        fun create():SearchFragmentComponent
    }
}