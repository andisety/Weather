package com.andi.weather

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andi.weather.ui.Besok
import com.andi.weather.ui.Hariini

class SetionPageAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment :Fragment? = null
//        val bundle = Bundle()

        when(position){
            0 -> {
                fragment = Hariini()

//                fragment.arguments = bundle

            }
            1 -> {
                fragment = Besok()
//                bundle.putString(MainActivity.DATA,username)
//                fragment.arguments = bundle
            }
        }
        return fragment as Fragment
    }
}