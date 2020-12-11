package com.example.framework_datasourcess.model.artwork


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Dimensions(
    @Embedded(prefix = "cm_")
    @SerializedName("cm")
    val cm: Cm?
)