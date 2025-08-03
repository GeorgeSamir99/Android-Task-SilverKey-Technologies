package com.example.androidtasksilverkeytechnologies.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtasksilverkeytechnologies.R
import com.example.androidtasksilverkeytechnologies.home.fragments.NewsItem

class NewsAdapter (private val newsList: List<NewsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.news_image)
        val newsTitle: TextView = itemView.findViewById(R.id.news_title)
        val newsTimestamp: TextView = itemView.findViewById(R.id.news_timestamp)
        val newsDescription: TextView = itemView.findViewById(R.id.news_description)
        val readMoreButton: TextView = itemView.findViewById(R.id.read_more_button)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.saved_mark_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.newsImage.setImageResource(news.imageResId)
        holder.newsTitle.text = news.title
        holder.newsTimestamp.text = news.timestamp
        holder.newsDescription.text = news.description
        // Optional: Add click listeners for Read More and Bookmark if needed
        // holder.readMoreButton.setOnClickListener { /* Handle click */ }
        // holder.bookmarkIcon.setOnClickListener { /* Handle click */ }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}