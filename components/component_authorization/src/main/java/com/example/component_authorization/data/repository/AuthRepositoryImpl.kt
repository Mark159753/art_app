package com.example.component_authorization.data.repository

import com.example.component_authorization.data.datasorcess.abstraction.LocalAuthDataSource
import com.example.component_authorization.data.datasorcess.abstraction.RemoteAuthDataSource
import com.example.component_authorization.domain.repository.AuthRepository
import com.example.core.scheduler.SchedulerProvider
import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource,
    private val schedulerProvider: SchedulerProvider
):AuthRepository{

    override fun authWithArtsyOAuth2(
        client_id: String,
        client_secret: String,
        code: String
    ): Single<AccessToken> {
        return remoteAuthDataSource.authWithArtsyOAuth2(client_id, client_secret, code)
                .subscribeOn(schedulerProvider.io())
                .flatMap {token ->
                    localAuthDataSource.cleanAccessToken()
                            .andThen(localAuthDataSource.saveAccessToken(token)
                                    .andThen(localAuthDataSource.getAccessToken()))
                }
    }

    override fun authWithLoggingPassword(
        client_id: String,
        client_secret: String,
        email: String,
        password: String
    ): Single<AccessToken> {
        return remoteAuthDataSource.authWithLoggingPassword(client_id, client_secret, email, password)
                .subscribeOn(schedulerProvider.io())
                .flatMap {token ->
                    localAuthDataSource.cleanAccessToken()
                            .andThen(localAuthDataSource.saveAccessToken(token)
                                    .andThen(localAuthDataSource.getAccessToken()))
                }
    }

    override fun authWithUsersOAuthToken(
        client_id: String,
        client_secret: String,
        oauth_token: String,
        oauth_provider: String
    ): Single<AccessToken> {
        return remoteAuthDataSource.authWithUsersOAuthToken(client_id, client_secret, oauth_token, oauth_provider)
                .subscribeOn(schedulerProvider.io())
                    .flatMap {token ->
                        localAuthDataSource.cleanAccessToken()
                                .andThen(localAuthDataSource.saveAccessToken(token)
                                        .andThen(localAuthDataSource.getAccessToken()))
                    }
    }
}