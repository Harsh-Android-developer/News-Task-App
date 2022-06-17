package com.example.newstaskapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstaskapp.model.NewsData
import com.example.newstaskapp.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel(){
    val newsList = MutableLiveData<NewsData>()
    val error = MutableLiveData<String>()

    fun getNewsList(){
        val newsResponse = newsRepository.getNewsList()
        newsResponse.enqueue(object : Callback<NewsData>{
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                if(response.isSuccessful){
                    newsList.postValue(response.body())
                }else{
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

    fun getAllNews(){
        val newsResponse = newsRepository.getAllNewsList()
        newsResponse.enqueue(object : Callback<NewsData>{
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                if(response.isSuccessful){
                    newsList.postValue(response.body())
                }else{
                    error.postValue(response.message())
                }
            }
            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

    fun getFilterNews(filter: String){
        val getFilter = newsList.value?.articles?.filter {
            it.title.lowercase(Locale.ROOT).contains(filter.lowercase(Locale.ROOT))
            }
        newsList.postValue(newsList.value?.copy(articles = getFilter!!))

    }


}
