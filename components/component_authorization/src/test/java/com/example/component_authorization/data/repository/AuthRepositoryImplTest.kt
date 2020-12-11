package com.example.component_authorization.data.repository

import com.example.component_authorization.dummy.datasourcess.LocalDataSourceTest
import com.example.component_authorization.dummy.datasourcess.RemoteDataSourceTest
import com.example.component_authorization.dummy.schedule.TestScheduler
import com.example.framework_datasourcess.model.AccessToken
import org.junit.Test

class AuthRepositoryImplTest{

    private val token = AccessToken("my_token", "today", 1)
    private val scheduler = TestScheduler()
    private val remote = RemoteDataSourceTest(token)
    private val local = LocalDataSourceTest()
    private val repository = AuthRepositoryImpl(remote, local, scheduler)

    @Test
    fun test_auth_repo(){
        repository.authWithArtsyOAuth2("", "", "")
            .subscribeOn(scheduler.ui())
            .test()
            .assertResult(token)
            .dispose()
    }
}