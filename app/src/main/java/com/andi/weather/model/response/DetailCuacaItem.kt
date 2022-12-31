package com.andi.weather.model.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailCuacaItem(
    @SerializedName("cuaca")
    val cuaca: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("jamCuaca")
    val jamCuaca: String,
    @SerializedName("kodeCuaca")
    val kodeCuaca: String,
    @SerializedName("tempC")
    val tempC: String,
    @SerializedName("tempF")
    val tempF: String
):Parcelable