package com.example.perludilindungi.api

import com.example.perludilindungi.models.FaskesResponse
import retrofit2.Response
import retrofit2.http.*

interface FaskesAPI {
    @GET("api/get-faskes-vaksinasi")
    suspend fun getFaskes(
        @Query("province") province: String,
        @Query("city") city: String,
    ): Response<FaskesResponse>
}