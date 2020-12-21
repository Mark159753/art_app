package com.example.profile_component.repository

import com.example.core.exception.NoConnectivityException
import com.example.core.model.UserProfileModel
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.mapper.UserDetailsMapper
import com.example.profile_component.datasources.abstraction.ProfileLocalDataSource
import com.example.profile_component.datasources.abstraction.ProfileRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val remote:ProfileRemoteDataSource,
    private val local:ProfileLocalDataSource,
    private val schedulerProvider: SchedulerProvider
):UserProfileRepository {

    override fun getUserDetails(): Single<UserProfileModel> {
        val mapper = UserDetailsMapper()
        return remote.getCurrentUser()
            .subscribeOn(schedulerProvider.io())
            .flatMap { remote.getUserDetails(it.id!!) }
            .flatMap { local.insertUserDetails(it)
                .andThen(local.getUserDetails())}
            .onErrorResumeNext {
                if (it is NoConnectivityException){
                    return@onErrorResumeNext local.getUserDetails()
                }else
                    throw  it
            }
            .map { mapper.map(it) }
            .observeOn(schedulerProvider.ui())
    }

    override fun logOut(): Completable {
        return remote.logOut()
                .subscribeOn(schedulerProvider.io())
                .flatMapCompletable{local.deleteUserToken().andThen(local.deleteUserDetails())}
                .observeOn(schedulerProvider.ui())
    }
}