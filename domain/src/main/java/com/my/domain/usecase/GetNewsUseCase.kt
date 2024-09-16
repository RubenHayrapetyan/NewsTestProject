package com.my.domain.usecase

import androidx.paging.PagingData
import com.my.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase {
  operator fun invoke(searchQuery: String? = null): Flow<PagingData<NewsModel>>
}