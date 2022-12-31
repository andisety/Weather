package com.andi.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andi.weather.model.response.DetailCuacaItem
import com.andi.weather.ui.Besok
import com.andi.weather.ui.Hariini

class SetionPageAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    var today:ArrayList<DetailCuacaItem>?=null
    var tom:ArrayList<DetailCuacaItem>?=null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment :Fragment? = null
        val bundle = Bundle()

        when(position){
            0 -> {
                fragment = Hariini()
                bundle.putParcelableArrayList("data",today)
                fragment.arguments = bundle

            }
            1 -> {

                fragment = Besok()
                bundle.putParcelableArrayList("data2",tom)
                fragment.arguments = bundle

            }
        }
        return fragment as Fragment
    }

    fun setData(paramToday:ArrayList<DetailCuacaItem>?, paramTom:ArrayList<DetailCuacaItem>?,){
        today?.clear()
        tom?.clear()
        today=paramToday
        tom = paramTom
    }

}