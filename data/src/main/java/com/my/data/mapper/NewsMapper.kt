package com.my.data.mapper

import com.my.data.model.local.NewsEntity
import com.my.data.model.remote.ApiNews
import com.my.domain.model.NewsModel
import com.my.domain.toDDmmYYYYhhmm

fun ApiNews.toNewsEntity(pageNumber: Int) = NewsEntity(
  id = id ?: "",
  webPublicationDate = webPublicationDate ?: "",
  webTitle = webTitle ?: "",
  thumbnailUrl = fields?.thumbnail ?: "",
  body = fields?.body ?: "",
  pageNumber = pageNumber
)

fun NewsEntity.toNewsModel() = NewsModel(
  id = id,
  webPublicationDate = webPublicationDate.toDDmmYYYYhhmm(),
  webTitle = webTitle,
  thumbnailUrl = thumbnailUrl,
  body = body,
  pageNumber = pageNumber
)