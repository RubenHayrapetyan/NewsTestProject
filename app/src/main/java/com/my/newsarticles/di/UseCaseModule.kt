package com.my.newsarticles.di

import com.my.domain.usecase.GetNewsUseCase
import com.my.domain.usecase.GetNewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
  @Binds
  abstract fun bindGetNewsUseCase(useCase: GetNewsUseCaseImpl): GetNewsUseCase
}