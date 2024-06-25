package com.my.domain.usecase

import androidx.paging.PagingData
import com.my.domain.entity.local.NewsEntity
import com.my.domain.repository.NewsPagerFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
  private val pagerFactory: NewsPagerFactory
) : GetNewsUseCase {

  override fun invoke(searchQuery: String?): Flow<PagingData<NewsEntity>> {
    val pager = pagerFactory.createPager(searchQuery)
    return pager.flow
  }
}