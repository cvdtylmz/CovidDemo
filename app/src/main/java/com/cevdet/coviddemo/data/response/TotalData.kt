package com.cevdet.coviddemo.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TotalData (
    @SerializedName("totalDeaths")
    @Expose
    val totalDeaths : String?,
    @SerializedName("totalCases")
    @Expose
    val totalCases : String?,
    @SerializedName("totalRecovered")
    @Expose
    val totalRecovered : String?
)