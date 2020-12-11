package com.example.future_authorization.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.future_authorization.OAuthSignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OAuthSignInViewModel::class)
    abstract fun bindOAuthSignInViewModel(model: OAuthSignInViewModel): ViewModel
}