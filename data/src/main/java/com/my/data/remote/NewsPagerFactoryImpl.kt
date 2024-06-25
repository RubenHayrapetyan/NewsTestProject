package com.my.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.my.data.local.NewsDatabase
import com.my.domain.entity.local.NewsEntity
import com.my.domain.repository.NewsPagerFactory
import javax.inject.Inject

class NewsPagerFactoryImpl @Inject constructor(
  private val newsArticlesDb: NewsDatabase,
  private val newsApiService: NewsApiService
): NewsPagerFactory {
  @OptIn(ExperimentalPagingApi::class)
  override fun createPager(searchQuery: String?): Pager<Int, NewsEntity> {
    return Pager(
      config = PagingConfig(pageSize = 10),
      remoteMediator = NewsMediator(
        newsDb = newsArticlesDb,
        newsApi = newsApiService,
        searchQuery = searchQuery
      ),
      pagingSourceFactory = {
        newsArticlesDb.newsDao.pagingSource()
      }
    )
  }
}