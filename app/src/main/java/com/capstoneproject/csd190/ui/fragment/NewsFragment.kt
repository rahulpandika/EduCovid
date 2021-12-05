package com.capstoneproject.csd190.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.csd190.databinding.FragmentNewsBinding
import com.capstoneproject.csd190.viewAdapter.NewsAdapter
import com.capstoneproject.csd190.viewModel.NewsViewModel
import kotlinx.coroutines.delay


class NewsFragment : Fragment() {

    private lateinit var newsFragmetBinding: FragmentNewsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsFragmetBinding = FragmentNewsBinding.inflate(layoutInflater,container,false)
        return newsFragmetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Loading(true)
        if(activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[NewsViewModel::class.java]
            val news = viewModel.getNews()
            val newsAdapter = NewsAdapter()
            newsAdapter.setNews(news)
            with(newsFragmetBinding.rvNews){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
                Loading(false)
            }
        }
    }
    private fun Loading(state: Boolean){
        if (state){
            newsFragmetBinding.progressBar.visibility = View.VISIBLE
        }
        else{
            newsFragmetBinding.progressBar.visibility = View.GONE
        }
    }
}