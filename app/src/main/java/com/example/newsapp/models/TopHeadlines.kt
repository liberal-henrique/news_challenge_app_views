package com.example.newsapp.models

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class News(
    val articles: List<Article>?,
    val status: String,
    val totalResults: Int
)

data class Article(
    val articleUUID : String = UUID.randomUUID().toString(),
    @SerializedName("author")
    val articleAuthor: String?,
    @SerializedName("content")
    val articleContent: String?,
    @SerializedName("description")
    val articleDescription: String?,
    @SerializedName("publishedAt")
    val articlePublishedAt: String?,
    @SerializedName("source")
    val articleSource: Source?,
    @SerializedName("title")
    val articleTitle: String?,
    @SerializedName("url")
    val articleUrl: String?,
    @SerializedName("urlToImage")
    val articleUrlToImage: String?
)