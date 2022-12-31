package com.andi.weather.model.response


import com.google.gson.annotations.SerializedName

data class ResponseWilayahItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("kecamatan")
    val kecamatan: String,
    @SerializedName("kota")
    val kota: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("propinsi")
    val propinsi: String
)