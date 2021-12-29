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

//    private val AUTH = "Basic "+ Base64.encodeToString("belalkhan:123456".toByteArray(), Base64.NO_WRAP)
//
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor { chain ->
//            val original = chain.request()
//
//            val requestBuilder = original.newBuilder()
//                .addHeader("Authorization", AUTH)
//                .method(original.method(), original.body())
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }.build()
//
//    val instance: UserApi by lazy{
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://meng-ngaji.herokuapp.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//
//        retrofit.create(UserApi::class.java)
//    }

}