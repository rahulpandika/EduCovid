package com.capstoneproject.csd190.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstoneproject.csd190.databinding.ActivityDetailNewsBinding
import com.capstoneproject.csd190.databinding.ContentDetailNewsBinding
import com.capstoneproject.csd190.model.NewsEntity
import com.capstoneproject.csd190.viewModel.DetailNewsViewModel

class DetailActivityNews : AppCompatActivity() {

    companion object{
        const val EXTRA_NEWS = "extra_news"
    }
    private lateinit var binding : ContentDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailNewsBinding = ActivityDetailNewsBinding.inflate(layoutInflater)
        binding = activityDetailNewsBinding.detailContent
        setContentView(activityDetailNewsBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailNewsViewModel::class.java]
        val news = intent.getParcelableExtra<NewsEntity>(EXTRA_NEWS) as NewsEntity
        viewModel.setNewsTitle(news.title)
        viewModel.getDetailNews()
        showDetail(news)

    }
    private fun showDetail(newsEntity: NewsEntity){
        Glide.with(this).load(newsEntity.imgSrc).into(binding.imgPoster)

        binding.tvTitle.text = newsEntity.title
        binding.tvText.text = newsEntity.text
        binding.tvSource.text = newsEntity.source

    }

}