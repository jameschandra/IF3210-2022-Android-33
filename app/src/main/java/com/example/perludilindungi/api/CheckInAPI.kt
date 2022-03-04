package com.example.perludilindungi.api

import com.example.perludilindungi.models.CheckInResponse
import retrofit2.Response
import okhttp3.RequestBody
import retrofit2.http.*

interface CheckInAPI {
    @POST("check-in")
    suspend fun postCheckIn(@Body requestBody: RequestBody): Response<CheckInResponse>
}