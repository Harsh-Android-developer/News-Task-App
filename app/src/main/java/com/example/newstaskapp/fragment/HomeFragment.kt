package com.example.newstaskapp.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstaskapp.adapter.NewsAdapter
import com.example.newstaskapp.databinding.FragmentHomeBinding
import com.example.newstaskapp.model.NewsData
import com.example.newstaskapp.modelFactory.NewsModelFactory
import com.example.newstaskapp.network.ApiResponse
import com.example.newstaskapp.repository.NewsRepository
import com.example.newstaskapp.viewModel.NewsViewModel

class HomeFragment : Fragment() {
    private lateinit var  viewBinding: FragmentHomeBinding
    private var newsAdapter : NewsAdapter? = null
    lateinit var newsViewModel: NewsViewModel
    private val apiResponse = ApiResponse.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        initViewModel()
        initSearchViewModel()
        return viewBinding.root
    }

    private fun setData(newsData: NewsData) {
        newsAdapter = NewsAdapter(newsData.articles, context!!)
        viewBinding.rvNews.layoutManager = LinearLayoutManager(activity!!,RecyclerView.VERTICAL,false)
        viewBinding.rvNews.adapter = newsAdapter
        newsAdapter!!.notifyDataSetChanged()
    }



    private fun initViewModel() {
        newsViewModel = ViewModelProvider(
            this, NewsModelFactory(NewsRepository(apiResponse))
        )
            .get(NewsViewModel::class.java)
        newsViewModel.newsList.observe(viewLifecycleOwner) {
            setData(it)
        }
        newsViewModel.error.observe(viewLifecycleOwner) {
        }

        newsViewModel.getNewsList()
    }

    private fun initSearchViewModel() {
        newsViewModel = ViewModelProvider(
            this, NewsModelFactory(NewsRepository(apiResponse))
        )
            .get(NewsViewModel::class.java)

        viewBinding.searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isEmpty()) {
                    newsViewModel.getNewsList()
                }else{
                    newsViewModel.getFilterNews(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

    }
}
