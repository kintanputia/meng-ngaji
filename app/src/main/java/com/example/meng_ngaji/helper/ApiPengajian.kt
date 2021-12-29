package com.example.meng_ngaji.helper

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiPengajian {
    @GET("pengajian")
    fun getPosts(): Call<ArrayList<PostResponse>>

}