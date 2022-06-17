package com.example.newstaskapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newstaskapp.fragment.FavFragment
import com.example.newstaskapp.fragment.HomeFragment
import com.example.newstaskapp.model.NewsData


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                HomeFragment()
            }
            1->{
                FavFragment()
            }
            else->{
                HomeFragment()
            }
        }

    }

    override fun getItemCount(): Int {
        return 2
    }

}

