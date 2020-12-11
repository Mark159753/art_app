package com.example.framework_datasourcess.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framework_datasourcess.model.ArtsyRemoteKeys

@Dao
interface ArtsyRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list:List<ArtsyRemoteKeys>)

    @Query("SELECT * FROM artsyRemoteKeys WHERE itemId = :itemId AND itemType = :itemType")
    fun getRemotesKeysByItemId(itemId:String, itemType:Int):ArtsyRemoteKeys

    @Query("DELETE FROM artsyRemoteKeys WHERE itemType = :itemType")
    fun deletesAllByType(itemType: Int)

    @Query("DELETE FROM artsyRemoteKeys")
    fun deleteAllKeys()
}