package com.example.meng_ngaji.helper

import retrofit2.Call
import retrofit2.http.GET

interface ApiDataPengajian {
    @GET("data")
    fun getPosts(): Call<ArrayList<Masjid>>
}