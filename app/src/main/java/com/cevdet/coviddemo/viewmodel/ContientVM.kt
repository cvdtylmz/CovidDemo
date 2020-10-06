package com.cevdet.coviddemo.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdet.coviddemo.data.response.BaseResponse
import com.cevdet.coviddemo.data.response.ContientData
import com.cevdet.coviddemo.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContientVM : ViewModel() {

    private lateinit var contientData: MutableLiveData<BaseResponse<List<ContientData>>>
    private lateinit var contientLoading: MutableLiveData<Boolean>
    private lateinit var contientError: MutableLiveData<Boolean>
    private lateinit var contientErrorMessage: MutableLiveData<String>


    private lateinit var contientCall: Call<BaseResponse<List<ContientData>>>
    private lateinit var apiClient: ApiClient


    fun getContientData(): LiveData<BaseResponse<List<ContientData>>> {
        return contientData
    }

    fun getContientLoading(): LiveData<Boolean> {
        return contientLoading
    }

    fun getContientError(): LiveData<Boolean> {
        return contientError
    }

    fun getContientErrorMessage(): LiveData<String> {
        return contientErrorMessage
    }


    fun fetchData(): Unit {
        contientLoading.value = true
        contientCall = apiClient.apiService.getContientData()
        contientCall.enqueue(object : Callback<BaseResponse<List<ContientData>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<ContientData>>>,
                response: Response<BaseResponse<List<ContientData>>>
            ) {
                contientLoading.value = false
                contientData.value = response.body()
                contientError.value = false
            }

            override fun onFailure(call: Call<BaseResponse<List<ContientData>>>, t: Throwable) {
                contientLoading.value = false
                contientError.value = false
                contientErrorMessage.value = t.message
            }
        })
    }

    override fun onCleared() {
        contientCall.cancel()
    }


}


