package com.my.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.my.data.local.NewsDatabase
import com.my.domain.entity.local.NewsEntity
import com.my.domain.entity.remote.ApiNewsResponse
import com.my.domain.mappers.toNewsEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsMediator(
  private val newsDb: NewsDatabase,
  private val newsApi: NewsApiService,
  private val searchQuery: String?
) : RemoteMediator<Int, NewsEntity>() {

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, NewsEntity>
  ): MediatorResult {
    return try {
      val offset = when (loadType) {
        LoadType.REFRESH -> 1
        LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        LoadType.APPEND -> {
          val lastItem = state.lastItemOrNull()
          if (lastItem == null || lastItem.pageNumber == 0) {
            return MediatorResult.Success(endOfPaginationReached = true)
          }else {
            lastItem.pageNumber.plus(1)
          }
        }
      }

      val response: ApiNewsResponse = newsApi.getNews(
        page = offset,
        apiKey = "dfc75ca7-deef-450f-8306-4e534f2457f6",
        searchQuery = searchQuery
      )

      val articles = response.response?.apiNews ?: emptyList()
      val endOfPaginationReached = articles.isEmpty()

      newsDb.withTransaction {
        if (loadType == LoadType.REFRESH) {
          newsDb.newsDao.clearAllArticles()
        }
        val articleEntities = articles.map { it.toNewsEntity(offset) }
        newsDb.newsDao.upsertAll(articleEntities)
      }

      MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    } catch (e: IOException) {
      MediatorResult.Error(e)
    } catch (e: HttpException) {
      MediatorResult.Error(e)
    }
  }
}
