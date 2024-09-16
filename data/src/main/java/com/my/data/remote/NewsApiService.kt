package com.my.data.remote

import com.my.data.model.remote.ApiNewsResponse
import com.my.data.util.DataConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
  @GET(
    "${DataConstants.BASE_ENDPOINT}?${DataConstants.PAGE_SIZE_KEY}=" +
        "${DataConstants.PAGE_SIZE_VALUE}&${DataConstants.SECTION_KEY}=" +
        "${DataConstants.SECTION_VALUE}&${DataConstants.FIELDS_KEY}=" +
        DataConstants.FIELDS_VALUE
  )
  suspend fun getNews(
    @Query(DataConstants.API_KEY) apiKey: String,
    @Query(DataConstants.PAGE) page: Int,
    @Query(DataConstants.QUERY) searchQuery: String? = null,
  ): ApiNewsResponse
}