package com.example.meng_ngaji.data_class

import java.text.SimpleDateFormat
import java.util.*

data class PostResponse (
    val id_masjid:Int,
    val nama_masjid:String?,
    val alamat_masjid:String?,
    val judul_pengajian:String?,
    val waktu_pengajian:String?,
    val tgl_pengajian: Date,
    val url_gambar:String
)
