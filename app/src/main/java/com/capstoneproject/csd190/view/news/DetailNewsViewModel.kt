package com.capstoneproject.csd190.view.news

import androidx.lifecycle.ViewModel
import com.capstoneproject.csd190.model.NewsEntity
import com.capstoneproject.csd190.utils.DummyNews

class DetailNewsViewModel : ViewModel() {
    private lateinit var newsTitle: String
    private fun getListNews(): ArrayList<NewsEntity> = DummyNews.generateDummyNews()

    fun setNewsTitle(newsTitle: String) {
        this.newsTitle = newsTitle
    }

    fun getDetailNews(): NewsEntity {
        lateinit var result: NewsEntity

        val listNews = getListNews()

        for (news in listNews) {
            if (news.title == newsTitle) {
                result = news

                break
            }
        }

        return result
    }
}