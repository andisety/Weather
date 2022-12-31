package com.andi.weather.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andi.weather.SetionPageAdapter
import com.andi.weather.model.response.DetailCuaca
import com.andi.weather.model.response.DetailCuacaItem
import com.andi.weather.model.response.ResponseWilayah
import com.andi.weather.network.RetrofitClient
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _responseWilayah = MutableLiveData<ResponseWilayah>()
    val responseWilayah:LiveData<ResponseWilayah> = _responseWilayah
    private val _responseDetailCuaca = MutableLiveData<DetailCuaca>()
    val responseDetailCuaca:LiveData<DetailCuaca> = _responseDetailCuaca


    init {
        getWilayah()
    }

    private fun getWilayah(){
        _isLoading.value = true
        val client = RetrofitClient.getApiService().getWilayah()
        client.enqueue(object: Callback<ResponseWilayah>{
            override fun onResponse(
                call: Call<ResponseWilayah>,
                response: Response<ResponseWilayah>
            ) {
                _isLoading.value = false
                _responseWilayah.value = response.body()

            }

            override fun onFailure(call: Call<ResponseWilayah>, t: Throwable) {
               Log.e("GET WILAYAH",t.toString())
            }

        })
    }

     fun showCuaca(id: String) {
         _isLoading.value=true
        RetrofitClient.getApiService().getDetailCuaca(id).enqueue(object:Callback<DetailCuaca>{
            override fun onResponse(call: Call<DetailCuaca>, response: Response<DetailCuaca>) {
                _isLoading.value=false
                _responseDetailCuaca.value = response.body()
            }

            override fun onFailure(call: Call<DetailCuaca>, t: Throwable) {
                Log.e("GET WILAYAH",t.toString())
            }

        })

    }

}