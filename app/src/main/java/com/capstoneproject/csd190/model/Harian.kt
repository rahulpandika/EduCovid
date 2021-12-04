package com.capstoneproject.csd190.model

data class Harian(
    val doc_count: Int,
    val jumlah_dirawat: JumlahDirawat,
    val jumlah_dirawat_kum: JumlahDirawatKum,
    val jumlah_meninggal: JumlahMeninggal,
    val jumlah_meninggal_kum: JumlahMeninggalKum,
    val jumlah_positif: JumlahPositif,
    val jumlah_positif_kum: JumlahPositifKum,
    val jumlah_sembuh: JumlahSembuh,
    val jumlah_sembuh_kum: JumlahSembuhKum,
    val key: Long,
    val key_as_string: String
)