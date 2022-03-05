package com.example.perludilindungi.repository

import com.example.perludilindungi.api.RetrofitInstance
import com.example.perludilindungi.models.CityResponse
import com.example.perludilindungi.models.FaskesResponse
import com.example.perludilindungi.models.NewsResponse
import com.example.perludilindungi.models.ProvinceResponse

import retrofit2.Response

class Repository {
    suspend fun getNews(): Response<NewsResponse> {
        return RetrofitInstance.newsInstance.getNews()
    }

    suspend fun getProvince(): Response<ProvinceResponse> {
        return RetrofitInstance.provinceInstance.getProvince()
    }

    suspend fun getCity(startId: String): Response<CityResponse> {
        return RetrofitInstance.cityInstance.getCity(startId)
    }

    suspend fun getFaskes(province: String, city: String): Response<FaskesResponse> {
        return RetrofitInstance.faskesInstance.getFaskes(province, city)
    }

}