package com.example.meng_ngaji.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiMaps {
    companion object {
        private const val BASE_URL = "https://maps.googleapis.com/maps/api/"
        fun getMaps(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}