package com.example.perludilindungi.models

data class ProvinceResponse (
    val curr_val: String,
    val message: String,
    val results: ArrayList<ProvinceData>,
)

data class ProvinceData (
    val key: String,
    val value: String,
)
