package com.example.meng_ngaji.data_class

import java.util.*

data class PengajianMasjid(
    val id_masjid:Int,
    val id_pengajian:Int,
    val judul_pengajian:String?,
    val waktu_pengajian:String?,
    val pengisi_kajian:String?,
    val tgl_pengajian: Date
    )
