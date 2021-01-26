package com.android.finalexam.model

data class JSON(
    val news: List<News>,
    val status: String,
    val totalResults: Int
)
