package com.example.newstaskapp.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newstaskapp.repository.NewsRepository
import com.example.newstaskapp.util.Constant.VIEW_MODEL_ERROR
import com.example.newstaskapp.viewModel.NewsViewModel

class NewsModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            NewsViewModel(this.newsRepository) as T
        } else {
            throw IllegalArgumentException(VIEW_MODEL_ERROR)
        }
    }
}
