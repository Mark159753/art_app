package com.example.profile_component.datasources.abstraction

import com.example.framework_datasourcess.model.user.UserDetails
import io.reactivex.Completable
import io.reactivex.Single

interface ProfileLocalDataSource {

    fun insertUserDetails(userDetails: UserDetails):Completable

    fun getUserDetails():Single<UserDetails>

    fun deleteUserToken():Completable

    fun deleteUserDetails():Completable
}