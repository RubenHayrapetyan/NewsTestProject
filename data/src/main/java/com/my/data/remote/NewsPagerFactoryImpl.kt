package com.my.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.my.data.local.NewsDatabase
import com.my.data.mapper.toNewsModel
import com.my.data.model.local.NewsEntity
import com.my.domain.model.NewsModel
import com.my.domain.repository.NewsPagerFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsPagerFactoryImpl @Inject constructor(
  private val newsArticlesDb: NewsDatabase,
  private val newsApiService: NewsApiService
): NewsPagerFactory {
  @OptIn(ExperimentalPagingApi::class)
  override fun createPager(searchQuery: String?): Flow<PagingData<NewsModel>> {
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
    ).flow.map { pagingData ->
      pagingData.map { newsEntity ->
        newsEntity.toNewsModel()
      }
    }
  }
}