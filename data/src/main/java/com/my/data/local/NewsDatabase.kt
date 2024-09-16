package com.my.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.data.model.local.NewsEntity

@Database(
  entities = [NewsEntity::class],
  version = 1
)
abstract class NewsDatabase: RoomDatabase() {

  abstract val newsDao: NewsDao
}