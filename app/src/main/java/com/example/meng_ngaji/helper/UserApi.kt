package com.example.meng_ngaji.helper

import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("no_hp") no_hp: String,
        @Field("password") password: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password: String
    ):Call<ResponModel>

}