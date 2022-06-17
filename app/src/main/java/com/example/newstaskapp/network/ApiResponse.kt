package com.example.newstaskapp.network

import com.example.newstaskapp.model.NewsData
import com.example.newstaskapp.util.Constant.API_KEY
import com.example.newstaskapp.util.Constant.BASE_URL
import com.example.newstaskapp.util.Constant.COUNTRY_KEY
import com.example.newstaskapp.util.Constant.END_POINT
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiResponse {

    @GET(END_POINT)
    fun getNewsList(
    @Query(COUNTRY_KEY) country: String,
    @Query(API_KEY) apiKey: String
    ) : Call<NewsData>

    @GET(END_POINT)
    fun getAllNewsList(
        @Query(COUNTRY_KEY) country: String,
        @Query(API_KEY) apiKey: String
    ) : Call<NewsData>

    companion object {
        private var apiResponse: ApiResponse? = null

        fun getInstance(): ApiResponse {
            if (apiResponse == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiResponse = retrofit.create(ApiResponse::class.java)
            }
            return apiResponse!!
        }
    }



}
