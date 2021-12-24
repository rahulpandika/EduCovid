package com.capstoneproject.csd190.viewModel

import androidx.lifecycle.ViewModel
import com.capstoneproject.csd190.model.NewsEntity
import com.capstoneproject.csd190.utils.DummyNews

class NewsViewModel:ViewModel(){
    fun getNews():List<NewsEntity> = DummyNews.generateDummyNews()
}