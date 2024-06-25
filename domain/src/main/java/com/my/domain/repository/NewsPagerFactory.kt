package com.my.domain.repository

import androidx.paging.Pager
import com.my.domain.entity.local.NewsEntity

interface NewsPagerFactory {
  fun createPager(searchQuery: String?): Pager<Int, NewsEntity>
}