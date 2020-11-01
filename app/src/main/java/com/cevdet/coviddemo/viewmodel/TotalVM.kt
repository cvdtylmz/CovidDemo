package com.cevdet.coviddemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdet.coviddemo.data.response.BaseResponse
import com.cevdet.coviddemo.data.response.TotalData
import com.cevdet.coviddemo.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalVM : ViewModel() {

    private val totalData: MutableLiveData<TotalData> = MutableLiveData()
    private val totalLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val totalErrorMessage: MutableLiveData<String> = MutableLiveData()


    private var totalCall: Call<BaseResponse<TotalData>>? = null


    fun getTotalData(): LiveData<TotalData> {
        return totalData
    }

    fun getTotalLoading(): LiveData<Boolean> {
        return totalLoading
    }


    fun getTotalErrorMessage(): LiveData<String> {
        return totalErrorMessage
    }


    fun fetchData() {
        totalLoading.value = true
        totalCall = ApiClient.apiService.getTotalData()
        totalCall?.enqueue(object : Callback<BaseResponse<TotalData>> {
            override fun onResponse(
                call: Call<BaseResponse<TotalData>>,
                response: Response<BaseResponse<TotalData>>
            ) {
                totalLoading.value = false
                totalData.value = response.body()?.data
                totalErrorMessage.value = null
            }

            override fun onFailure(call: Call<BaseResponse<TotalData>>, t: Throwable) {
                totalLoading.value = false
                totalErrorMessage.value = t.message.toString()
            }
        })
    }

    override fun onCleared() {
        totalCall?.cancel()
    }

}


