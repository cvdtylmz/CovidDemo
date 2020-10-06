package com.cevdet.coviddemo.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContientData (
    @SerializedName("contient")
    @Expose
    val contient : String ?,
    @SerializedName("totalCases")
    @Expose
    val totalCases : String ?,
    @SerializedName("newCases")
    @Expose
    val newCases : String ?,
    @SerializedName("totalDeaths")
    @Expose
    val totalDeaths : String ?,
    @SerializedName("newDeaths")
    @Expose
    val newDeaths : String ?,
    @SerializedName("totalRecovered")
    @Expose
    val totalRecovered : String ?,
    @SerializedName("activeCases")
    @Expose
    val activeCases : String ?
)
