package com.example.profile_component.datasources.remote

import com.example.framework_datasourcess.model.DeleteAccessToken
import com.example.framework_datasourcess.model.user.CurrentUser
import com.example.framework_datasourcess.model.user.UserDetails
import com.example.framework_datasourcess.remote.main.ArtsyService
import com.example.profile_component.datasources.abstraction.ProfileRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val api:ArtsyService
):ProfileRemoteDataSource {

    override fun getCurrentUser(): Single<CurrentUser> {
        return api.getCurrentUser()
    }

    override fun getUserDetails(id: String): Single<UserDetails> {
        return api.getUserDetails(id)
    }

    override fun logOut(): Single<DeleteAccessToken> {
        return api.logout()
    }
}