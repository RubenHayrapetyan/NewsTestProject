package com.my.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.my.data.util.DataConstants

@Entity(tableName = DataConstants.ARTICLES_TABLE)
data class NewsEntity(
  @PrimaryKey
  val id: String,
  val webPublicationDate: String,
  val webTitle: String,
  val thumbnailUrl: String,
  val body: String,
  val pageNumber: Int,
)