package com.example.framework_datasourcess.di

import android.content.Context
import com.example.core.BuildConfig
import com.example.core.di.MainInterceptorOkHttpClient
import com.example.framework_datasourcess.BASE_URL
import com.example.framework_datasourcess.local.dao.AccessTokenDao
import com.example.framework_datasourcess.remote.interceptors.AuthInterceptor
import com.example.framework_datasourcess.remote.interceptors.NetworkConnectionInterceptor
import com.example.framework_datasourcess.remote.main.ArtsyService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideNetworkConnectionInterceptor(context: Context):NetworkConnectionInterceptor{
        return NetworkConnectionInterceptor(context)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthInterceptorInterceptor(accessTokenDao: AccessTokenDao):AuthInterceptor{
        return AuthInterceptor(accessTokenDao)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        val logger = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) logger.level = HttpLoggingInterceptor.Level.BODY
        else logger.level = HttpLoggingInterceptor.Level.BASIC
        return logger
    }

    @JvmStatic
    @MainInterceptorOkHttpClient
    @Provides
    fun provideOkHttpClient(
        logging:HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        connectionInterceptor: NetworkConnectionInterceptor
    ):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(connectionInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideArtService(@MainInterceptorOkHttpClient okHttpClient:OkHttpClient):ArtsyService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
            .create(ArtsyService::class.java)
    }
}