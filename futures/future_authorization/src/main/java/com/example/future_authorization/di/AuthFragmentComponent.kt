package com.example.future_authorization.di

import com.example.component_authorization.di.DataSourcesModule
import com.example.core.di.AuthFragmentScope
import com.example.framework_datasourcess.di.AuthNetworkModule
import com.example.future_authorization.AuthorizationActivity
import com.example.future_authorization.LoginPasswordSignInFragment
import dagger.Subcomponent

@AuthFragmentScope
@Subcomponent(
    modules = [
        AuthNetworkModule::class,
        DataSourcesModule::class,
        AuthViewModelModule::class
    ]
)
interface AuthFragmentComponent {

    fun inject(activity: AuthorizationActivity)

    fun inject(fragment: LoginPasswordSignInFragment)


    @Subcomponent.Factory
    interface Factory{
        fun create():AuthFragmentComponent
    }
}