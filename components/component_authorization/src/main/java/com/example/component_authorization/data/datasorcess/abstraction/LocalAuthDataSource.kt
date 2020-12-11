package com.example.component_authorization.data.datasorcess.abstraction

import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.Completable
import io.reactivex.Single

interface LocalAuthDataSource {

    fun saveAccessToken(token: AccessToken): Completable

    fun getAccessToken(): Single<AccessToken>

    fun cleanAccessToken():Completable
}