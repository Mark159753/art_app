package com.example.artapp.di

import com.example.artapp.navigation.AppNavigator
import com.example.core.navagation.AuthorizationToMainActivityNav
import com.example.core.navagation.SearchNav
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator():AppNavigator{
        return AppNavigator()
    }

    @Provides
    fun provideAuthToMainActivity(nav:AppNavigator):AuthorizationToMainActivityNav{
        return nav
    }

    @Provides
    fun provideSearchDialogNav(nav:AppNavigator):SearchNav{
        return nav
    }

}