package com.odhiambodevelopers.okhttp3_retrofit_demo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.odhiambodevelopers.okhttp3_retrofit_demo.databinding.ActivityMainBinding
import com.odhiambodevelopers.okhttp3_retrofit_demo.model.Dog
import com.odhiambodevelopers.okhttp3_retrofit_demo.network.DogApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val client = OkHttpClient()
        val url = "https://dog.ceo/api/breeds/image/random"
        val request = Request.Builder()
            .url(url)
            .build()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.btnRetrofit.setOnClickListener {
           DogApi.apiService.getRandomDog().enqueue(object: Callback<Dog> {
               override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                   binding.textViewok.text = response.body().toString()
               }

               override fun onFailure(call: Call<Dog>, t: Throwable) {
                   Log.d(TAG, "onFailure: ${t.message}")
               }

           })
       }

        binding.btnOkhttp3.setOnClickListener {

            client.newCall(request).enqueue(object: okhttp3.Callback{
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d(TAG, "onFailure: ${e.message}")
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful){
                        //converts data to a readable form
                        val jsonData: ResponseBody? = response.body
                        val result = JSONObject(jsonData?.string())

                        //setting data to the ui thread
                        runOnUiThread {
                            binding.textViewok.text = result.toString()
                        }
                    }
                }

            })
        }
    }
}