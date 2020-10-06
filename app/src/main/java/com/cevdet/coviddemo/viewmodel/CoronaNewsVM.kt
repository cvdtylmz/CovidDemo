package com.cevdet.coviddemo.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdet.coviddemo.data.response.BaseResponse
import com.cevdet.coviddemo.data.response.CoronaNews
import com.cevdet.coviddemo.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoronaNewsVM : ViewModel() {

    private lateinit var coronaNews: MutableLiveData<BaseResponse<List<CoronaNews>>>
    private lateinit var coronaNewsLoading: MutableLiveData<Boolean>
    private lateinit var coronaNewsError: MutableLiveData<Boolean>
    private lateinit var coronaNewsErrorMessage: MutableLiveData<String>


    private lateinit var coronaNewsCall: Call<BaseResponse<List<CoronaNews>>>
    private lateinit var apiClient: ApiClient


    fun getCoronaNewsData(): LiveData<BaseResponse<List<CoronaNews>>> {
        return coronaNews
    }

    fun getCoronaNewsLoading(): LiveData<Boolean> {
        return coronaNewsLoading
    }

    fun getCoronaNewsError(): LiveData<Boolean> {
        return coronaNewsError
    }

    fun getCoronaNewsErrorMessage(): LiveData<String> {
        return coronaNewsErrorMessage
    }


    fun fetchData(): Unit {
        coronaNewsLoading.value = true
        coronaNewsCall = apiClient.apiService.getCoronaNewsData()
        coronaNewsCall.enqueue(object : Callback<BaseResponse<List<CoronaNews>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<CoronaNews>>>,
                response: Response<BaseResponse<List<CoronaNews>>>
            ) {
                coronaNewsLoading.value = false
                coronaNews.value = response.body()
                coronaNewsError.value = false
            }

            override fun onFailure(call: Call<BaseResponse<List<CoronaNews>>>, t: Throwable) {
                coronaNewsLoading.value = false
                coronaNewsError.value = false
                coronaNewsErrorMessage.value = t.message
            }
        })
    }

    override fun onCleared() {
        coronaNewsCall.cancel()
    }


}


