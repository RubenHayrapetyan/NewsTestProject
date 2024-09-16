package com.my.domain.repository

import androidx.paging.PagingData
import com.my.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsPagerFactory {
  fun createPager(searchQuery: String?): Flow<PagingData<NewsModel>>
}