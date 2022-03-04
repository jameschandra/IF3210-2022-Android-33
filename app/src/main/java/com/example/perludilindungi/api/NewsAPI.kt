package com.example.perludilindungi.api

import com.example.perludilindungi.models.NewsResponse
import retrofit2.Response
import retrofit2.http.*

interface NewsAPI {
    @GET("api/get-news")
    suspend fun getNews(): Response<NewsResponse>
}