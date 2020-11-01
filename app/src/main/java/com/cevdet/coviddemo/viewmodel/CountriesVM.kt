package com.cevdet.coviddemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdet.coviddemo.data.response.BaseResponse
import com.cevdet.coviddemo.data.response.CountriesData
import com.cevdet.coviddemo.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesVM : ViewModel() {
    private val countriesData: MutableLiveData<BaseResponse<List<CountriesData>>> = MutableLiveData()
    private val countriesLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val countriesError: MutableLiveData<Boolean> = MutableLiveData()
    private val countriesErrorMessage: MutableLiveData<String> = MutableLiveData()


    private var countriesCall: Call<BaseResponse<List<CountriesData>>>? = null


    fun getCountriesData(): LiveData<BaseResponse<List<CountriesData>>> {
        return countriesData
    }

    fun getCountriesLoading(): LiveData<Boolean> {
        return countriesLoading
    }

    fun getCountriesError(): LiveData<Boolean> {
        return countriesError
    }

    fun getCountriesErrorMessage(): LiveData<String> {
        return countriesErrorMessage
    }


    fun fetchData(): Unit {
        countriesLoading.value = true
        countriesCall = ApiClient.apiService.getCountriesData()
        countriesCall?.enqueue(object : Callback<BaseResponse<List<CountriesData>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<CountriesData>>>,
                response: Response<BaseResponse<List<CountriesData>>>
            ) {
                countriesLoading.value = false
                countriesData.value = response.body()
                countriesError.value = false
            }

            override fun onFailure(call: Call<BaseResponse<List<CountriesData>>>, t: Throwable) {
                countriesLoading.value = false
                countriesError.value = false
                countriesErrorMessage.value = t.message
            }
        })
    }

    override fun onCleared() {
        countriesCall?.cancel()
    }


}


