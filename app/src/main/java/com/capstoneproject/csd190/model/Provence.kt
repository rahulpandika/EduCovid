package com.capstoneproject.csd190.model

import com.squareup.moshi.Json

data class Provence(
    val key: String,
    @Json(name = "jumlah_dirawat") val dirawat: Int,
    val lokasi: Location
)