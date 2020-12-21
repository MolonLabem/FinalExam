package com.android.finalexam.repository

import com.android.finalexam.api.NewsApiService

class NewsRepository(private val apiService: NewsApiService) {
    fun getNews(category: String, country: String) = apiService.getNews(category, country)
}