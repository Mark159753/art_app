package com.example.profile_component.repository

import com.example.core.model.UserProfileModel
import io.reactivex.Completable
import io.reactivex.Single

interface UserProfileRepository {

    fun getUserDetails():Single<UserProfileModel>

    fun logOut():Completable
}