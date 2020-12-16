package com.example.artapp.di

import android.content.Context
import com.example.artapp.SplashActivity
import com.example.framework_datasourcess.di.DatabaseModule
import com.example.framework_datasourcess.di.NetworkModule
import com.example.future_artwork_details.di.DetailsArtworkComponent
import com.example.future_authorization.di.AuthFragmentComponent
import com.example.future_home.di.HomeFragmentComponent
import com.example.future_search.di.SearchFragmentComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class,
        SchedulerModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun getAuthFragmentComponentFactory():AuthFragmentComponent.Factory

    fun getHomeFragmentComponentFactory(): HomeFragmentComponent.Factory

    fun getSearchFragmentComponentFactory():SearchFragmentComponent.Factory

    fun getDetailsArtworkComponentFactory(): DetailsArtworkComponent.Factory

    fun inject(activity:SplashActivity)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context):ApplicationComponent
    }
}