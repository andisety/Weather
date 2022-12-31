package com.andi.weather.network

import com.andi.weather.model.response.DetailCuaca
import com.andi.weather.model.response.ResponseWilayah
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("cuaca/wilayah.json")
    fun getWilayah(): Call<ResponseWilayah>

    @GET("cuaca/{id}.json")
    fun getDetailCuaca(
        @Path("id")id:String
    ):Call<DetailCuaca>
}