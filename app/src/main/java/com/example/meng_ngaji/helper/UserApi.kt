package com.example.meng_ngaji.helper

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("api/login")
    fun login(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @GET("profil")
    fun getProfil() : Call<List<Profile>>

    @GET("profil/detail")
    fun getDetailProfil() : Call<List<Profile>>
}