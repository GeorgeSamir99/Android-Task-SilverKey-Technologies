package com.example.androidtasksilverkeytechnologies.ui

import com.example.androidtasksilverkeytechnologies.news_api.model.Article

interface ArticleClickListener {
    fun onArticleClicked(article: Article)
}