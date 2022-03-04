package com.example.perludilindungi.api

import com.example.perludilindungi.models.CityResponse
import retrofit2.Response
import retrofit2.http.*

interface CityAPI {
    @GET("api/get-city")
    suspend fun getCity(
        @Query("start_id") startId: String
    ): Response<CityResponse>
}