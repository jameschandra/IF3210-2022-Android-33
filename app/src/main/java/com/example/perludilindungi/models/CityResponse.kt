package com.example.perludilindungi.models

data class CityResponse (
    val curr_val: String,
    val message: String,
    val results: ArrayList<CityData>,
)

data class CityResponseBadRequest (
    val curr_val: String,
    val message: String,
)

data class CityData (
    val key: String,
    val value: String,
)