package com.cevdet.coviddemo.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoronaNews(
    @SerializedName("key")
    @Expose
    val id : String?,
    @SerializedName("url")
    @Expose
    val newsUrl : String?,
    @SerializedName("description")
    @Expose
    val desc : String?,
    @SerializedName("image")
    @Expose
    val imageUrl : String?,
    @SerializedName("source")
    @Expose
    val newsSource : String?
)