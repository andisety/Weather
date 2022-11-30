package com.andi.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.andi.weather.adapter.Cuaca
import com.andi.weather.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    var spinner:Spinner?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kota= listOf("Jakarta","Jogja","solo")

        //read asset
        val file_name = "wilayah.json"
        val json_string = application.assets.open(file_name).bufferedReader().use{
            it.readText()
        }
        val gson = Gson()
        val listPersonType = object : TypeToken<List<Cuaca>>() {}.type
        val cuaca:List<Cuaca> = gson.fromJson(json_string,listPersonType)
        Log.e("log",json_string)





//        binding.subTitle.text = json_string


        spinner = binding.mySpinner
        spinner!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, kota)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)


        val adapter = SetionPageAdapter(this)
        val title = arrayOf("Hari ini","Besok")

        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tbLayout,binding.viewPager){tab,position ->
            tab.text = title[position]
        }.attach()

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Log.e("select","ok")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Log.e("noselect","no")
    }
}