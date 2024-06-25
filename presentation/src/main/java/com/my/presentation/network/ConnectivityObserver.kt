package com.my.presentation.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
  fun observe(): Flow<Status>

  enum class Status {
    Available,
    Lost
  }
}