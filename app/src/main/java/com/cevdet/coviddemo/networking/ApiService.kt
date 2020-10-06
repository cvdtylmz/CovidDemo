package com.cevdet.coviddemo.networking

import com.cevdet.coviddemo.data.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("/corona/countriesData")
    fun getCountriesData () : Call<BaseResponse<List<CountriesData>>>

    @GET("/corona/totalData")
    fun getTotalData () : Call<BaseResponse<TotalData>>

    @GET("/corona/coronaNews")
    fun getCoronaNewsData () : Call<BaseResponse<List<CoronaNews>>>

    @GET("/corona/continentData")
    fun getContientData () : Call<BaseResponse<List<ContientData>>>

}