package com.capstoneproject.csd190.view.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.csd190.databinding.FragmentNewsBinding
import com.capstoneproject.csd190.view.adapter.NewsAdapter

class NewsFragment : Fragment() {
    private lateinit var newsFragmentBinding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsFragmentBinding = FragmentNewsBinding.inflate(layoutInflater, container, false)

        return newsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading(true)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[NewsViewModel::class.java]

            val news = viewModel.getNews()
            val newsAdapter = NewsAdapter()
            newsAdapter.setNews(news)

            with(newsFragmentBinding.rvNews) {
                layoutManager = LinearLayoutManager(context)

                setHasFixedSize(true)

                adapter = newsAdapter

                loading(false)
            }
        }
    }

    private fun loading(state: Boolean) {
        if (state) {
            newsFragmentBinding.progressBar.visibility = View.VISIBLE
        } else {
            newsFragmentBinding.progressBar.visibility = View.GONE
        }
    }
}