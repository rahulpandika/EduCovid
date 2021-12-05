package com.capstoneproject.csd190.viewAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstoneproject.csd190.R
import com.capstoneproject.csd190.databinding.ItemNewsBinding
import com.capstoneproject.csd190.model.NewsEntity
import com.capstoneproject.csd190.ui.activity.DetailActivityNews
import kotlin.math.acos

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var listNews = ArrayList<NewsEntity>()


    fun setNews(news: List<NewsEntity>?){
        if (news == null) return
            this.listNews.clear()
            this.listNews.addAll(news)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val itemNewsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(itemNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        val news = listNews[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = listNews.size

    class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(news : NewsEntity){
        with(binding){
            tvTitle.text = news.title
            tvDesc.text = news.description
            Glide.with(itemView.context).load(news.imgSrc).apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error)).into(imageviewNews)
            itemView.setOnClickListener {
                val intent  = Intent(itemView.context, DetailActivityNews::class.java)
                intent.putExtra(DetailActivityNews.EXTRA_NEWS,news)
                itemView.context.startActivity(intent)
            }
          }
        }
    }
}