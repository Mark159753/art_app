package com.example.future_profile.di

import com.example.core.di.ProfileFragmentScope
import com.example.future_profile.ProfileFragment
import com.example.profile_component.di.ProfileDataSourceModule
import dagger.Subcomponent


@ProfileFragmentScope
@Subcomponent(
    modules = [
        ProfileDataSourceModule::class,
        ProfileViewModelModule::class
    ]
)
interface ProfileFragmentComponent {

    fun inject(fragment:ProfileFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():ProfileFragmentComponent
    }
}