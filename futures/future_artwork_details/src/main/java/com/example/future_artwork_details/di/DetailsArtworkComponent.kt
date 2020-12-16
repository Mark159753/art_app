package com.example.future_artwork_details.di

import com.example.artwork_details_component.di.ArtworkDetailsDataSourceModule
import com.example.artwork_details_component.di.ArtworkDetailsUseCaseModule
import com.example.core.di.DetailsArtworkScope
import com.example.future_artwork_details.ArtworkDetailsFragment
import dagger.Subcomponent

@DetailsArtworkScope
@Subcomponent(
        modules = [
            ArtworkDetailsDataSourceModule::class,
            ArtworkDetailsUseCaseModule::class,
            DetailsArtworkViewModelModule::class
        ]
)
interface DetailsArtworkComponent {

    fun inject(fragment:ArtworkDetailsFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():DetailsArtworkComponent
    }
}