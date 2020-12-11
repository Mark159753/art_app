package com.example.future_home.di

import com.example.core.di.HomeFragmentScope
import com.example.future_home.HomeFragment
import com.example.home_component.di.DataSourcesModule
import com.example.home_component.di.HomeRepositoryModule
import com.example.home_component.di.HomeUseCaseModule
import dagger.Subcomponent

@HomeFragmentScope
@Subcomponent(
    modules = [
        DataSourcesModule::class,
        HomeRepositoryModule::class,
        HomeUseCaseModule::class,
        HomeViewModelModule::class
    ]
)
interface HomeFragmentComponent {

    fun inject(fragment:HomeFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():HomeFragmentComponent
    }
}