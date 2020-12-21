package com.example.profile_component.datasources.abstraction

import com.example.framework_datasourcess.model.DeleteAccessToken
import com.example.framework_datasourcess.model.user.CurrentUser
import com.example.framework_datasourcess.model.user.UserDetails
import io.reactivex.Single

interface ProfileRemoteDataSource {

    fun getCurrentUser(): Single<CurrentUser>

    fun getUserDetails(id:String):Single<UserDetails>

    fun logOut():Single<DeleteAccessToken>
}