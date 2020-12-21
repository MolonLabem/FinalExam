package com.android.finalexam.repository

import com.android.finalexam.api.NewsApiService

object NewsRepositoryProvider {
    fun provideNewsRepository(): NewsRepository {
        return NewsRepository(apiService = NewsApiService.create())
    }
}