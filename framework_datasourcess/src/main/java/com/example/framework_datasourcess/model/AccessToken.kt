package com.example.framework_datasourcess.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "access_token")
data class AccessToken(
    val access_token: String,
    val expires_in: String,
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
)