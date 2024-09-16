package com.my.domain.usecase

import androidx.paging.PagingData
import com.my.domain.model.NewsModel
import com.my.domain.repository.NewsPagerFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
  private val pagerFactory: NewsPagerFactory
) : GetNewsUseCase {

  override fun invoke(searchQuery: String?): Flow<PagingData<NewsModel>> {
    val pager = pagerFactory.createPager(searchQuery)
    return pager
  }
}