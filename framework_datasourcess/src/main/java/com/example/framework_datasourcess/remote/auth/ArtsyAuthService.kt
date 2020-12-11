package com.example.framework_datasourcess.remote.auth

import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ArtsyAuthService {

/*
    Don't forget do request before call method authWithArtsyOAuth2(...)
        fun redirectUserToBrowser(client_id:String, redirect_uri:String){
        val uri = Uri.parse("https://api.artsy.net/oauth2/authorize?client_id=$client_id&redirect_uri=$redirect_uri&response_type=code")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }*/

    @FormUrlEncoded
    @POST("oauth2/access_token")
    fun authWithArtsyOAuth2(
        @Field("client_id") client_id:String,
        @Field("client_secret") client_secret:String,
        @Field("code") code:String,
        @Field("grant_type") grant_type:String = "authorization_code",
        @Field("scope") scope:String = "offline_access"
    ): Single<AccessToken>

    @FormUrlEncoded
    @POST("oauth2/access_token ")
    fun authWithLoggingPassword(
        @Field("client_id") client_id:String,
        @Field("client_secret") client_secret:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("grant_type") grant_type:String = "credentials",
        @Field("scope") scope:String = "offline_access"
    ):Single<AccessToken>

    @FormUrlEncoded
    @POST("oauth2/access_token ")
    fun authWithUsersOAuthToken(
        @Field("client_id") client_id:String,
        @Field("client_secret") client_secret:String,
        @Field("oauth_token") oauth_token:String,
        @Field("oauth_provider") oauth_provider:String,
        @Field("grant_type") grant_type:String = "oauth_token",
        @Field("scope") scope:String = "offline_access"
    ):Single<AccessToken>
}