package com.example.meng_ngaji.helper

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiPengajian {
    @GET("pengajian")
    fun getPosts(): Call<ArrayList<PostResponse>>

    @FormUrlEncoded
    @POST("cari_pengajian")
    fun searchPengajian(
        @Field("nama_masjid") nama_masjid:String?
    ): Call<ArrayList<Masjid>>

    @FormUrlEncoded
    @POST("daftar_pengajian")
    fun daftarPengajian(
        @Field("id_masjid") id_masjid:Int
    ): Call<ArrayList<PengajianMasjid>>
}