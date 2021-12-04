package com.capstoneproject.csd190.model

import com.squareup.moshi.Json

data class DailyCase(
    val key: Long,
    @Json(name = "jumlah_positif") val jumlahPositif: Value
)