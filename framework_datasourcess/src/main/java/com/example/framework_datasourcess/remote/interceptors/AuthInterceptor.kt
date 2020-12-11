package com.example.framework_datasourcess.remote.interceptors

import com.example.framework_datasourcess.local.dao.AccessTokenDao
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val accessTokenDao: AccessTokenDao) :Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = accessTokenDao.getAccessTokenSync()

        val header = chain.request()
            .headers
            .newBuilder()
            .add(X_ACCESS_TOKEN, token.access_token)
            .build()


        val newUrl = chain.request()
            .newBuilder()
            .headers(header)
            .build()
        return chain.proceed(newUrl)
    }

    companion object{
        private const val X_ACCESS_TOKEN = "X-Access-Token"
    }
}