package com.example.androidtasksilverkeytechnologies.news_api.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NewsResponse(
    val status: String? = null,
    val source: String? = null,
    val sortBy: String? = null,
    val articles: List<Article>? = null
)
@Parcelize
data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?
):Parcelable {
    fun getSafeTitle() = title ?: "No Title"
    fun getSafeDescription() = description ?: "No Description"
    fun getSafePublishedAt() = publishedAt ?: "Unknown Date"
    fun getSafeUrl() = url ?: ""
    fun getSafeAuthor() = author ?: "Unknown Author"
}

