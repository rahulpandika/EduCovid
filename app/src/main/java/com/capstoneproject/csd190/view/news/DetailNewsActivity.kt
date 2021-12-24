package com.capstoneproject.csd190.view.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstoneproject.csd190.databinding.ActivityDetailNewsBinding
import com.capstoneproject.csd190.databinding.ContentDetailNewsBinding
import com.capstoneproject.csd190.model.NewsEntity

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var detailNewsBinding: ContentDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailNewsBinding = ActivityDetailNewsBinding.inflate(layoutInflater)
        detailNewsBinding = activityDetailNewsBinding.detailContent

        setContentView(activityDetailNewsBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailNewsViewModel::class.java]

        val news = intent.getParcelableExtra<NewsEntity>(EXTRA_NEWS) as NewsEntity
        viewModel.setNewsTitle(news.title)
        viewModel.getDetailNews()

        showDetail(news)
    }

    private fun showDetail(newsEntity: NewsEntity) {
        Glide.with(this).load(newsEntity.imgSrc).into(detailNewsBinding.imgPoster)
        detailNewsBinding.tvTitleNews.text = newsEntity.title
        detailNewsBinding.tvDescNews.text = newsEntity.text
        detailNewsBinding.tvSourceNews.text = newsEntity.source
    }

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }
}