package com.my.domain.entity.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class NewsEntity(
  @PrimaryKey
  val id: String,
  val webPublicationDate: String,
  val webTitle: String,
  val thumbnailUrl: String,
  val body: String,
  val pageNumber: Int,
): Parcelable
