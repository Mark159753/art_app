package com.example.profile_component.datasources.local

import com.example.framework_datasourcess.local.dao.AccessTokenDao
import com.example.framework_datasourcess.local.dao.UserDetailsDao
import com.example.framework_datasourcess.model.user.UserDetails
import com.example.profile_component.datasources.abstraction.ProfileLocalDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProfileLocalDataSourceImpl @Inject constructor (
    private val dao: UserDetailsDao,
    private val tokenDao: AccessTokenDao
):ProfileLocalDataSource {

    override fun insertUserDetails(userDetails: UserDetails):Completable {
        return dao.insert(userDetails)
    }

    override fun getUserDetails(): Single<UserDetails> {
        return dao.getUser()
    }

    override fun deleteUserDetails():Completable {
        return dao.deleteAll()
    }

    override fun deleteUserToken(): Completable {
        return tokenDao.clearAllAccessToken()
    }
}