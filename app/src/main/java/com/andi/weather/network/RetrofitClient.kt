package com.andi.weather.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        fun getApiService():ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://ibnux.github.io/BMKG-importer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }



}