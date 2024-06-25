package com.my.data.remote

import com.my.domain.entity.remote.ApiNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
  @GET("search?page-size=10&section=news&show-fields=thumbnail,body")
  suspend fun getNews(
    @Query("api-key") apiKey: String,
    @Query("page") page: Int,
    @Query("q") searchQuery: String? = null,
  ): ApiNewsResponse
}