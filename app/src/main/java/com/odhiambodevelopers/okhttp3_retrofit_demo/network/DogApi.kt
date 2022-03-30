package com.odhiambodevelopers.okhttp3_retrofit_demo.network

import com.odhiambodevelopers.okhttp3_retrofit_demo.constant.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}