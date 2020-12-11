package com.example.core.scheduler

import io.reactivex.Scheduler


interface SchedulerProvider {
    fun io(): Scheduler

    fun computation():Scheduler

    fun ui():Scheduler
}