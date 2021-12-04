package com.capstoneproject.csd190.api

import com.capstoneproject.csd190.BuildConfig
import com.capstoneproject.csd190.model.ProvenceList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object ApiCovid19 {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BuildConfig.BASE_URL)
        .build()
    val service: ApiService by lazy { retrofit.create(ApiService::class.java) }

    interface ApiService {
        @GET("prov.json")
        suspend fun getProvinsi(): ProvenceList
    }
}