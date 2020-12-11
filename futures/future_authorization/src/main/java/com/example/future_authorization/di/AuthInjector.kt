package com.example.future_authorization.di

interface AuthInjector {

    fun getAuthFragmentComponentFactory():AuthFragmentComponent.Factory
}