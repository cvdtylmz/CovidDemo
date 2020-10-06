package com.cevdet.coviddemo.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountriesData (
    @SerializedName("country")
    @Expose
    val country : String?,
    @SerializedName("totalcases")
    @Expose
    val totalCases : String?,
    @SerializedName("newCases")
    @Expose
    val newCases : String?,
    @SerializedName("totaldeaths")
    @Expose
    val totalDeaths : String?,
    @SerializedName("newDeaths")
    @Expose
    val newDeaths : String?,
    @SerializedName("totalRecovered")
    @Expose
    val totalRecovered : String?,
    @SerializedName("activeCases")
    @Expose
    val activeCases : String?
)