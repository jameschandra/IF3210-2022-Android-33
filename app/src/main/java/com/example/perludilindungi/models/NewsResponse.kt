package com.example.perludilindungi.models

data class NewsResponse(
    val success: Boolean,
    val message: String,
    val count_total: Int,
    val results: ArrayList<NewsData>,
)

data class NewsData (
    val title: String,
    val link: List<String>,
    val guid: String,
    val pubDate: String,
    val description: Description,
    val enclosure: Enclosure,
)

data class Description (
    val __cdata: String,
)

data class Enclosure (
    val _url: String,
    val _length: String,
    val _type: String,
)