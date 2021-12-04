package com.capstoneproject.csd190.model

data class Update(
    val harian: List<Harian>,
    val penambahan: Penambahan,
    val total: Total
)