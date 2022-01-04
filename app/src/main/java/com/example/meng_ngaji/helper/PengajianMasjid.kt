package com.example.meng_ngaji.helper

import java.util.*

data class PengajianMasjid(
    val id_masjid:Int,
    val judul_pengajian:String?,
    val waktu_pengajian:String?,
    val pengisi_kajian:String?,
    val tgl_pengajian: Date
    )
