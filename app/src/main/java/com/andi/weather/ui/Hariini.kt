package com.andi.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andi.weather.R
import com.andi.weather.adapter.CuacaAadapter
import com.andi.weather.model.response.DetailCuacaItem

class Hariini : Fragment() {

    lateinit var rcList:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hariini, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txt = arguments?.getParcelableArrayList<DetailCuacaItem>("data")
        initView(view)
        val adapterC = txt?.let { CuacaAadapter(it) }
        rcList.adapter = adapterC
        rcList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }


    fun initView(view: View){
        rcList = view.findViewById(R.id.rcListHariini)

    }

}