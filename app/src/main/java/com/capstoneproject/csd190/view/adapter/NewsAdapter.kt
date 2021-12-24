package com.capstoneproject.csd190.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstoneproject.csd190.R
import com.capstoneproject.csd190.databinding.ItemNewsBinding
import com.capstoneproject.csd190.model.NewsEntity
import com.capstoneproject.csd190.view.news.DetailNewsActivity

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var listNews = ArrayList<NewsEntity>()

    fun setNews(news: List<NewsEntity>?) {
        if (news == null) return
        this.listNews.clear()
        this.listNews.addAll(news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemNewsBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(itemNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = listNews[position]

        holder.bind(news)
    }

    override fun getItemCount(): Int = listNews.size

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsEntity) {
            with(binding) {
                tvTitleNews.text = news.title
                tvDescNews.text = news.description

                Glide.with(itemView.context).load(news.imgSrc).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).into(ivNews)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS, news)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}