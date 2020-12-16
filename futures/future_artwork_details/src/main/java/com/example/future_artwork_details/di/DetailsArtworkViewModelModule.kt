package com.example.future_artwork_details.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.future_artwork_details.ArtworkDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsArtworkViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArtworkDetailsViewModel::class)
    abstract fun bindHomeViewModel(model: ArtworkDetailsViewModel): ViewModel
}