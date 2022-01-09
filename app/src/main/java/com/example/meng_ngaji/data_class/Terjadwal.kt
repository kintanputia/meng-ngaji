package com.example.meng_ngaji.data_class

import java.util.*

data class Terjadwal(
    val id_pf:Int,
    val id_user:Int,
    val id_pengajian:Int,
    val judul_pengajian:String?,
    val waktu_pengajian:String?,
    val tgl_pengajian: Date
)
