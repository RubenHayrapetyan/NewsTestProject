package com.my.newsarticles.di

import android.net.ConnectivityManager
import com.my.presentation.network.ConnectivityObserver
import com.my.presentation.network.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

  @Provides
  fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return NetworkConnectivityObserver(connectivityManager)
  }
}