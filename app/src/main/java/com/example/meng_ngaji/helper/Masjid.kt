package com.example.meng_ngaji.helper

import com.google.gson.annotations.SerializedName

data class Masjid(
    val judul:String,
    val waktu:String,
    val jarak:String,
    @SerializedName("nama_masjid")
    val namaMasjid:String?
)
