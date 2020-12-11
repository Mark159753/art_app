package com.example.component_authorization.data.datasorcess.implementation

import com.example.component_authorization.data.datasorcess.abstraction.LocalAuthDataSource
import com.example.framework_datasourcess.local.dao.AccessTokenDao
import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(private val accessTokenDao: AccessTokenDao): LocalAuthDataSource {

    override fun saveAccessToken(token: AccessToken): Completable = accessTokenDao.insertAccessToken(token)

    override fun getAccessToken(): Single<AccessToken> = accessTokenDao.getAccessToken()

    override fun cleanAccessToken(): Completable = accessTokenDao.clearAllAccessToken()
}