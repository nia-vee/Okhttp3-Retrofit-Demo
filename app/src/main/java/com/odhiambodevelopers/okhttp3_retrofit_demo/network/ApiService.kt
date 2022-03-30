package com.odhiambodevelopers.okhttp3_retrofit_demo.network

import com.odhiambodevelopers.okhttp3_retrofit_demo.model.Dog
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("image/random")
    fun getRandomDog() : Call<Dog>
}