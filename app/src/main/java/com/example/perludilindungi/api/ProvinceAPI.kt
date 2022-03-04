package com.example.perludilindungi.api

import com.example.perludilindungi.models.ProvinceResponse
import retrofit2.Response
import retrofit2.http.*

interface ProvinceAPI {
    @GET("api/get-province")
    suspend fun getProvince(): Response<ProvinceResponse>
}