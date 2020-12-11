package com.example.component_authorization.data.datasorcess.implementation

import com.example.component_authorization.data.datasorcess.abstraction.RemoteAuthDataSource
import com.example.framework_datasourcess.model.AccessToken
import com.example.framework_datasourcess.remote.auth.ArtsyAuthService
import io.reactivex.Single
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(private val service:ArtsyAuthService): RemoteAuthDataSource {

    override fun authWithArtsyOAuth2(
        client_id: String,
        client_secret: String,
        code: String
    ): Single<AccessToken> = service.authWithArtsyOAuth2(client_id, client_secret, code)

    override fun authWithLoggingPassword(
        client_id: String,
        client_secret: String,
        email: String,
        password: String
    ): Single<AccessToken> = service.authWithLoggingPassword(client_id, client_secret, email, password)

    override fun authWithUsersOAuthToken(
        client_id: String,
        client_secret: String,
        oauth_token: String,
        oauth_provider: String
    ): Single<AccessToken>  = service.authWithUsersOAuthToken(client_id, client_secret, oauth_token, oauth_provider)
}