package com.example.androidtasksilverkeytechnologies.news_api.model


data class NewsResponse(
    val status: String? = null,
    val source: String? = null,
    val sortBy: String? = null,
    val articles: List<Article>? = null
)

data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?
) {
    fun getSafeTitle() = title ?: "No Title"
    fun getSafeDescription() = description ?: "No Description"
    fun getSafePublishedAt() = publishedAt ?: "Unknown Date"
    fun getSafeUrl() = url ?: ""
    fun getSafeAuthor() = author ?: "Unknown Author"
}

