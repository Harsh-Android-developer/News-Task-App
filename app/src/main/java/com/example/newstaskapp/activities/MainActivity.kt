package com.example.newstaskapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.newstaskapp.R
import com.example.newstaskapp.adapter.ViewPagerAdapter
import com.example.newstaskapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(viewBinding.root)
        initViews()
    }


    private fun initViews() {
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewBinding.viewPager.adapter = viewPagerAdapter

        viewBinding.viewPager.registerOnPageChangeCallback(object :  ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> viewBinding.bottomNavigation.menu.findItem(R.id.home).isChecked = true
                    1 -> viewBinding.bottomNavigation.menu.findItem(R.id.fav).isChecked = true
                    }
                }
            }
        )


        viewBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home->{
                    viewBinding.viewPager.currentItem = 0
                    true
                }
                R.id.fav->{
                    viewBinding.viewPager.currentItem = 1
                    true
                }
                else ->false
            }
        }
    }
}

