package com.example.framework_datasourcess.di

import com.example.core.di.AuthFragmentScope
import com.example.core.di.AuthInterceptorOkHttpClient
import com.example.framework_datasourcess.BASE_URL
import com.example.framework_datasourcess.remote.auth.ArtsyAuthService
import com.example.framework_datasourcess.remote.interceptors.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object AuthNetworkModule {

    @JvmStatic
    @AuthInterceptorOkHttpClient
    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        connectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(connectionInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @JvmStatic
    @Provides
    @AuthFragmentScope
    fun provideArtAuthService(@AuthInterceptorOkHttpClient okHttpClient:OkHttpClient): ArtsyAuthService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
            .create(ArtsyAuthService::class.java)
    }
}