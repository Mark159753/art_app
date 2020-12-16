package com.example.artapp

import android.app.Application
import com.example.artapp.di.ApplicationComponent
import com.example.artapp.di.DaggerApplicationComponent
import com.example.future_artwork_details.di.ArtworkDetailsInjector
import com.example.future_artwork_details.di.DetailsArtworkComponent
import com.example.future_authorization.di.AuthFragmentComponent
import com.example.future_authorization.di.AuthInjector
import com.example.future_home.di.HomeFragmentComponent
import com.example.future_home.di.HomeInjector
import com.example.future_search.di.SearchFragmentComponent
import com.example.future_search.di.SearchInjector
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger


class ArtApp:Application(), AuthInjector, HomeInjector, SearchInjector, ArtworkDetailsInjector {

    lateinit var appComponent:ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }

    override fun getAuthFragmentComponentFactory(): AuthFragmentComponent.Factory {
        return appComponent.getAuthFragmentComponentFactory()
    }

    override fun getHomeFragmentComponentFactory(): HomeFragmentComponent.Factory {
        return appComponent.getHomeFragmentComponentFactory()
    }

    override fun getSearchComponentFactory(): SearchFragmentComponent.Factory {
        return appComponent.getSearchFragmentComponentFactory()
    }

    override fun getDetailsArtworkComponentFactory(): DetailsArtworkComponent.Factory {
        return appComponent.getDetailsArtworkComponentFactory()
    }
}