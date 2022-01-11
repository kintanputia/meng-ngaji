package com.example.meng_ngaji.helper

import com.example.meng_ngaji.data_class.*
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

    @FormUrlEncoded
    @POST("add_pf")
    fun addPf(
        @Field("id_pengajian") id_pengajian:Int,
        @Field("id_user") id_user:Int
    ): Call<ArrayList<Terjadwal>>

    @FormUrlEncoded
    @POST("pengajian_favorit")
    fun getPf(
        @Field("id_user") id_user: Int
    ): Call<ArrayList<Terjadwal>>

    @FormUrlEncoded
    @POST("pengajian_terdekat")
    fun getPt(
        @Field("id_user") id_user: Int
    ): Call<ArrayList<PengajianTerdekat>>
}