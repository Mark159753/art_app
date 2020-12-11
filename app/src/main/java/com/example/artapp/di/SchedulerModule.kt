package com.example.artapp.di

import com.example.core.scheduler.SchedulerProvider
import com.example.core.scheduler.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SchedulerModule {

    @Provides
    @Singleton
    fun provideScheduleProvider():SchedulerProvider{
        return SchedulerProviderImpl()
    }
}