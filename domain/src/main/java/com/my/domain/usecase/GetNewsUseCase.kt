package com.my.domain.usecase

import androidx.paging.PagingData
import com.my.domain.entity.local.NewsEntity
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase {
  operator fun invoke(searchQuery: String? = null): Flow<PagingData<NewsEntity>>
}