package com.example.future_profile.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.future_profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindHomeViewModel(model: ProfileViewModel): ViewModel
}