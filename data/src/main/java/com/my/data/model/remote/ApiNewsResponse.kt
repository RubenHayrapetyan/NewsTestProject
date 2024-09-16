package com.my.data.model.remote

import com.squareup.moshi.Json

data class ApiNewsResponse(
  val response: ApiData?
)

data class ApiData(
  val status: String?,
  val total: Int?,
  val startIndex: Int?,
  val pageSize: Int?,
  val currentPage: Int?,
  val pages: Int?,
  val orderBy: String?,
  @field:Json(name = "results")
  val apiNews: List<ApiNews>?
)

data class ApiNews(
  val id: String?,
  val type: String?,
  val sectionId: String?,
  val sectionName: String?,
  val webPublicationDate: String?,
  val webTitle: String?,
  val webUrl: String?,
  val apiUrl: String?,
  @field:Json(name = "fields")
  val fields: ApiFields?,
  val isHosted: Boolean?,
  val pillarId: String?,
  val pillarName: String?
)

data class ApiFields(
  val thumbnail: String?,
  val body: String?,
)