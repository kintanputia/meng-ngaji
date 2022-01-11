package com.example.meng_ngaji.helper

import android.util.Base64
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance(): Retrofit {
        val gson = GsonBuilder(). setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://meng-ngaji.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}