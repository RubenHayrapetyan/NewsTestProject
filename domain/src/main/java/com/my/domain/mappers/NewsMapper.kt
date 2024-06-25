package com.my.domain.mappers

import com.my.domain.entity.remote.ApiNews
import com.my.domain.entity.local.NewsEntity
import com.my.domain.toDDmmYYYYhhmm

fun ApiNews.toNewsEntity(pageNumber: Int): NewsEntity {
  return NewsEntity(
    id = id ?: "",
    webPublicationDate = webPublicationDate?.toDDmmYYYYhhmm() ?: "",
    webTitle = webTitle ?: "",
    thumbnailUrl = fields?.thumbnail ?: "",
    body = fields?.body ?: "",
    pageNumber = pageNumber
  )
}