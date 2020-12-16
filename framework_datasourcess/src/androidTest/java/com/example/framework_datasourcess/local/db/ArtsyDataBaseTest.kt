package com.example.framework_datasourcess.local.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.framework_datasourcess.local.dao.AccessTokenDao
import com.example.framework_datasourcess.model.AccessToken
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class ArtsyDataBaseTest {

    private lateinit var dataBase: ArtsyDataBase
    private lateinit var accessTokenDao: AccessTokenDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        dataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
            ArtsyDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        accessTokenDao = dataBase.getAccessTokenDao()
    }


    @Test
    fun checkIfAccessTokenExist(){
        val token = AccessToken("my_token", "today", 1)

//        accessTokenDao.getAccessToken().test()
//            .assertError(EmptyResultSetException::class.java)

        accessTokenDao.insertAccessToken(token).test()
            .assertComplete()

        accessTokenDao.getAccessToken().test()
            .assertValue(token)
            .assertComplete()

        accessTokenDao.clearAllAccessToken().test()
            .assertComplete()

    }


    @After
    fun tearDown() {
        dataBase.close()
    }


}