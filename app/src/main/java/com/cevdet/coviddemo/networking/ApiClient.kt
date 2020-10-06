package com.cevdet.coviddemo.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {

    private var retrofit : Retrofit
    var apiService : ApiService


    init {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader(
                        ApiConstants.CONTENT_TYPE,
                        ApiConstants.APP_JSON)
                    .addHeader(
                            ApiConstants.AUTH,
                            ApiConstants.API_KEY)
                    .build()

                    it.proceed(request)
                }
                .addInterceptor(interceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create()
        }

}