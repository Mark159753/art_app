package com.example.component_authorization.data.datasorcess.abstraction

import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.Single

interface RemoteAuthDataSource {

    fun authWithArtsyOAuth2(
        client_id:String,
        client_secret:String,
        code:String
    ): Single<AccessToken>

    fun authWithLoggingPassword(
        client_id:String,
        client_secret:String,
        email:String,
        password:String
    ):Single<AccessToken>

    fun authWithUsersOAuthToken(
        client_id:String,
        client_secret:String,
        oauth_token:String,
        oauth_provider:String
    ):Single<AccessToken>
}