package com.cevdet.coviddemo.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<out T>(
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("result")
    @Expose
    val data: T?
)