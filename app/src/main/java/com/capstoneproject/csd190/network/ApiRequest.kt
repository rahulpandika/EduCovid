package com.capstoneproject.csd190.network

import com.capstoneproject.csd190.model.CaseCovidIndonesia
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("/public/api/update.json")
    fun getCovidIndonesia(): Call<CaseCovidIndonesia>
}