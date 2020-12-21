package com.example.framework_datasourcess.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framework_datasourcess.model.user.UserDetails
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item:UserDetails):Completable

    @Query("SELECT * FROM userDetails")
    fun getUser():Single<UserDetails>

    @Query("DELETE FROM userDetails")
    fun deleteAll():Completable
}