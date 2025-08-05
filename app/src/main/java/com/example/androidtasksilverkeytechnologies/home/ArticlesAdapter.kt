package com.example.androidtasksilverkeytechnologies.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtasksilverkeytechnologies.R
import com.example.androidtasksilverkeytechnologies.databinding.NewsItemBinding

import com.example.androidtasksilverkeytechnologies.news_api.model.Article

class ArticlesAdapter(private var articlesList: List<Article>)  : RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>(){


    @SuppressLint("NotifyDataSetChanged")
    fun updateArticles(newArticles: List<Article>) {
        articlesList = newArticles
        notifyDataSetChanged()
        Log.e("ArticlesAdapter", "Updated ${articlesList.size} articles")
    }


    class ArticlesViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding   = NewsItemBinding.inflate(layoutInflater,parent,false)
        return ArticlesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article = articlesList[position]
        holder.binding.newsTitle.text = article.getSafeTitle()
        holder.binding.newsTimestamp.text = article.getSafePublishedAt()
        holder.binding.newsDescription.text = article.getSafeDescription()

        Glide.with(holder.binding.root)
            .load(article.urlToImage)
            .placeholder(R.drawable.test_image)
            .error(R.drawable.test_image)
            .into(holder.binding.newsImage)
    }

    override fun getItemCount(): Int = articlesList.size
}