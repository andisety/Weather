package com.andi.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andi.weather.R
import com.andi.weather.model.response.DetailCuacaItem
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CuacaAadapter(private val listCuaca:ArrayList<DetailCuacaItem>):RecyclerView.Adapter<CuacaAadapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val jam: TextView = itemView.findViewById(R.id.tvJam)
        val img: ImageView = itemView.findViewById(R.id.ivAwan)
        val suhu: TextView = itemView.findViewById(R.id.tvSuhu)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cuaca, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cuaca = listCuaca[position]
        val formatterOut = SimpleDateFormat("HH:mm")
        val formatterIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: Date =formatterIn.parse(cuaca.jamCuaca)
        var jam = formatterOut.format(date)

        holder.jam.text = jam
        holder.suhu.text = cuaca.tempC+"°"
        Glide.with(holder.itemView)
            .load("https://ibnux.github.io/BMKG-importer/icon/"+ cuaca.kodeCuaca +".png")
            .into(holder.img)

    }

    override fun getItemCount(): Int {
        return listCuaca.size
    }
}