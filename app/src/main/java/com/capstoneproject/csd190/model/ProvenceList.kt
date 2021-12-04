package com.capstoneproject.csd190.model

import com.squareup.moshi.Json

data class ProvenceList(
    @Json(name = "list_data") val data: List<Provence>
)