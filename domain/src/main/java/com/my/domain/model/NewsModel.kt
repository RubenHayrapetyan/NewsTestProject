package com.my.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
  val id: String,
  val webPublicationDate: String,
  val webTitle: String,
  val thumbnailUrl: String,
  val body: String,
  val pageNumber: Int,
): Parcelable