package com.example.framework_datasourcess.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framework_datasourcess.model.AccessToken
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AccessTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccessToken(token: AccessToken): Completable

    @Query("SELECT * FROM access_token")
    fun getAccessToken(): Single<AccessToken>

    @Query("SELECT * FROM access_token")
    fun getAccessTokenSync(): AccessToken

    @Query("DELETE FROM access_token")
    fun clearAllAccessToken():Completable
}