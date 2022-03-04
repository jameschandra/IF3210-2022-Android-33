package com.example.perludilindungi.api

import com.example.perludilindungi.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val newsInstance: NewsAPI by lazy {
        retrofit.create(NewsAPI::class.java)
    }

    val provinceInstance: ProvinceAPI by lazy {
        retrofit.create(ProvinceAPI::class.java)
    }

    val cityInstance: CityAPI by lazy {
        retrofit.create(CityAPI::class.java)
    }

    val faskesInstance: FaskesAPI by lazy {
        retrofit.create(FaskesAPI::class.java)
    }

    val checkInInstance: CheckInAPI by lazy {
        retrofit.create(CheckInAPI::class.java)
    }
}