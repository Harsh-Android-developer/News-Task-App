package com.example.newstaskapp.repository

import com.example.newstaskapp.network.ApiResponse
import com.example.newstaskapp.util.Constant.API_KEY_VALUE
import com.example.newstaskapp.util.Constant.COUNTRY_IN_NAME
import com.example.newstaskapp.util.Constant.COUNTRY_US_NAME

class NewsRepository(private val apiResponse: ApiResponse){

    fun getNewsList() = apiResponse.getNewsList(COUNTRY_IN_NAME,API_KEY_VALUE)

    fun getAllNewsList() = apiResponse.getAllNewsList(COUNTRY_US_NAME,API_KEY_VALUE)



}
