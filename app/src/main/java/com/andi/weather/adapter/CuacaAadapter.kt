package com.andi.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andi.weather.R
import com.andi.weather.model.CauacaViewPage

class CuacaAadapter(private val listCuaca:ArrayList<CauacaViewPage>):RecyclerView.Adapter<CuacaAadapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val jam: TextView = itemView.findViewById(R.id.tvJam)
        val img: ImageView = itemView.findViewById(R.id.ivAwan)
        val suhu: TextView = itemView.findViewById(R.id.tvSuhu)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cuaca, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuaca = listCuaca[position]
        holder.jam.text = cuaca.jam
        holder.suhu.text = cuaca.suhu
//        holder.jam.text = cuaca.jam

    }

    override fun getItemCount(): Int {
        return listCuaca.size
    }
}