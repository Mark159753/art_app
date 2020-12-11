package com.example.component_authorization.dummy.datasourcess

import com.example.component_authorization.data.datasorcess.abstraction.RemoteAuthDataSource
import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.rxjava3.core.Single

class RemoteDataSourceTest(
    private val token: AccessToken
): RemoteAuthDataSource {


    override fun authWithArtsyOAuth2(
        client_id: String,
        client_secret: String,
        code: String
    ): Single<AccessToken> {
        return Single.just(token)
    }

    override fun authWithLoggingPassword(
        client_id: String,
        client_secret: String,
        email: String,
        password: String
    ): Single<AccessToken> {
        return Single.just(token)
    }

    override fun authWithUsersOAuthToken(
        client_id: String,
        client_secret: String,
        oauth_token: String,
        oauth_provider: String
    ): Single<AccessToken> {
        return Single.just(token)
    }
}