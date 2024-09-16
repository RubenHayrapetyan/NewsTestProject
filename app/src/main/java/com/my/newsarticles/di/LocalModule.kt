package com.my.newsarticles.di

import android.content.Context
import androidx.room.Room
import com.my.data.local.NewsDao
import com.my.data.local.NewsDatabase
import com.my.newsarticles.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
    return Room.databaseBuilder(context, NewsDatabase::class.java, AppConstants.DB_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao
}