package com.andi.weather

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.andi.weather.databinding.ActivityMainBinding
import com.andi.weather.helpers.DateHelper
import com.andi.weather.model.response.DetailCuaca
import com.andi.weather.model.response.DetailCuacaItem

import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import com.andi.weather.model.MainViewModel


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private var listIdWilayah=ArrayList<String>()
    private var listNamaWilayah=ArrayList<String>()
    private var listKecWilayah=ArrayList<String>()
    lateinit var adapter: SetionPageAdapter
    var spinner:Spinner?=null
    lateinit var mainModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

         mainModel  = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        mainModel.isLoading.observe(this){isLoading->
            showLoading(isLoading)
        }
        mainModel.responseWilayah.observe(this) { responseWilayah ->
            responseWilayah.forEach {
                listIdWilayah.add(it.id)
                listKecWilayah.add(it.kecamatan)
                listNamaWilayah.add(it.propinsi + " | " + it.kota)
            }
            setupSpinner()
        }



    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pgBar.visibility = View.VISIBLE
        } else {
            binding.pgBar.visibility = View.GONE
        }
    }

    private fun setupSpinner() {
        spinner = binding.mySpinner
        spinner?.setOnItemSelectedListener(this@MainActivity)
        val adapter = ArrayAdapter(this@MainActivity,R.layout.spiner_list_down,listNamaWilayah)
        spinner?.setAdapter(adapter)
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
        if(parent?.selectedItem==binding.mySpinner.selectedItem){
            binding.tvKec.text = listKecWilayah[position]
            mainModel.showCuaca(listIdWilayah[position])

            mainModel.responseDetailCuaca.observe(this){detail->
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                var current = formatter.format(time)
                val firstapiformat =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

                val today:ArrayList<DetailCuacaItem> = ArrayList()
                val tom:ArrayList<DetailCuacaItem> = ArrayList()

                val datePhone = LocalDate.parse(current , firstapiformat)

                detail?.forEach {
                    val dateApi = LocalDate.parse(it.jamCuaca , firstapiformat)
                    if(dateApi==datePhone){
                        today.add(it)
                    }else if (datePhone.plusDays(1)==dateApi){
                        tom.add(it)
                    }
                }

                adapter = SetionPageAdapter(this@MainActivity)
                val title = arrayOf("Hari ini","Besok")
                binding.viewPager.adapter = adapter
                adapter.setData(today,tom)

                TabLayoutMediator(binding.tbLayout,binding.viewPager){tab,position ->
                    tab.text = title[position]
                }.attach()
                setMainText(detail)
            }
        }
    }

    private fun setMainText(detail:DetailCuaca ) {
//        val firstapiformat =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val jam0 = arrayListOf<String>("22","23","00","01","02",)
        val jam6 = arrayListOf<String>("03","04","05","06","07","08","09")
        val jam12 = arrayListOf<String>("10","11","12","13","14","15")
        val jam18 = arrayListOf<String>("16","17","18","19","20","21")

        val formatter = SimpleDateFormat("HH")

        val jam = formatter.format(DateHelper.time)

        if (jam0.contains(jam)){
            setDetailCuacaSesuaiJam(detail,0)
        }else if (jam6.contains(jam)){
            setDetailCuacaSesuaiJam(detail,1)
        }else if (jam12.contains(jam)){
            setDetailCuacaSesuaiJam(detail,2)
        }else if (jam18.contains(jam)){
            setDetailCuacaSesuaiJam(detail,3)
        }

    }

    fun setDetailCuacaSesuaiJam(detail: DetailCuaca,idx:Int){

        if (detail.isNullOrEmpty()){
            Toast.makeText(applicationContext,"DATA TIDAK ADA",Toast.LENGTH_SHORT).show()
        }else{
            val time = DateHelper.time
            val formatter2 = SimpleDateFormat("EEEE, dd MMMM HH:mm")
            val current = formatter2.format(time)
            binding.tvSuhu.text= detail?.get(idx)?.tempC+"°"
            binding.tvStatus.text= detail?.get(idx)?.cuaca
            binding.tvDate.text = current
            Glide.with(this)
                .load("https://ibnux.github.io/BMKG-importer/icon/"+ detail?.get(idx)?.kodeCuaca +".png")
                .into(binding.ivCuaca)
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Log.e("noselect","no")
    }



}