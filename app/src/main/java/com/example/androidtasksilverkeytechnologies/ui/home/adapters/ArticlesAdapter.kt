package com.example.androidtasksilverkeytechnologies.ui.home.adapters

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
import com.example.androidtasksilverkeytechnologies.ui.ArticleClickListener

class ArticlesAdapter(private var articlesList: List<Article?>,
                      private val articleClickListener: ArticleClickListener,
                     )  : RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>(){


    @SuppressLint("NotifyDataSetChanged")
    fun updateArticles(newArticles: List<Article?>) {
        val diffCallback = ArticlesDiffCallback(articlesList, newArticles)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        articlesList = newArticles
        diffResult.dispatchUpdatesTo(this) }


    class ArticlesViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding   = NewsItemBinding.inflate(layoutInflater,parent,false)
        return ArticlesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article = articlesList[position]
        holder.binding.newsTitle.text = article?.getSafeTitle()
        holder.binding.newsTimestamp.text = article?.getSafePublishedAt()
        holder.binding.newsDescription.text = article?.getSafeDescription()

        Glide.with(holder.binding.root)
            .load(article?.urlToImage)
            .placeholder(R.drawable.test_image)
            .error(R.drawable.test_image)
            .into(holder.binding.newsImage)

        holder.binding.readMoreButton.setOnClickListener {
            article?.let { articleClickListener.onArticleClicked(it)}
        }
    }

    override fun getItemCount(): Int = articlesList.size
    private class ArticlesDiffCallback(
        private val oldList: List<Article?>,
        private val newList: List<Article?>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]?.url == newList[newItemPosition]?.url
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}