package com.example.meng_ngaji.helper

import com.google.gson.annotations.SerializedName
import java.util.*

data class PostResponse (
//    val id:Int,
    val nama_masjid:String?,
    val judul_pengajian:String?,
    val waktu_pengajian:String?,
    val tgl_pengajian:Date
//    @SerializedName("body")
//    val text:String?
)
