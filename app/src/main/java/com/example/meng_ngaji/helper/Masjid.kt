package com.example.meng_ngaji.helper

import java.util.*

data class Masjid(
    val id_masjid:Int,
    val nama_masjid:String?,
    val alamat_masjid:String?,
    val judul_pengajian:String?,
    val waktu_pengajian:String?,
    val tgl_pengajian: Date
)
