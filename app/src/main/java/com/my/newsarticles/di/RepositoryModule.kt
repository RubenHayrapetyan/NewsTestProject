package com.my.newsarticles.di

import com.my.data.remote.NewsPagerFactoryImpl
import com.my.domain.repository.NewsPagerFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoryModule {
  @Binds
  abstract fun bindNewsPagerFactory(factory: NewsPagerFactoryImpl): NewsPagerFactory
}