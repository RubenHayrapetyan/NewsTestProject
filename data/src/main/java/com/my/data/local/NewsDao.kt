package com.my.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.my.data.model.local.NewsEntity

@Dao
interface NewsDao {

  @Upsert
  suspend fun upsertAll(articles: List<NewsEntity>)

  @Query("SELECT * FROM articles")
  fun pagingSource(): PagingSource<Int, NewsEntity>

  @Query("DELETE FROM articles")
  suspend fun clearAllArticles()
}