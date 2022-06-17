package com.example.newstaskapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsData(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) : Serializable
