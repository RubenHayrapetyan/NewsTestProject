package com.my.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.my.domain.entity.local.NewsEntity
import com.my.domain.usecase.GetNewsUseCase
import com.my.presentation.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val getNewsUseCase: GetNewsUseCase,
  private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

  private val _newsPagingFlow by lazy { MutableStateFlow<PagingData<NewsEntity>>(PagingData.empty()) }
  val newsPagingFlow: Flow<PagingData<NewsEntity>> = _newsPagingFlow.cachedIn(viewModelScope)

  private val _hasInternet by lazy { MutableStateFlow(false) }
  val hasInternet: StateFlow<Boolean> = _hasInternet

  init {
    observeConnectivity()
    searchNews("")
  }

  fun searchNews(query: String?) {
    viewModelScope.launch {
      _newsPagingFlow.emit(PagingData.empty())
        getNewsUseCase(query)
          .cachedIn(viewModelScope)
          .collectLatest {
          _newsPagingFlow.emit(it)
        }
      }
  }

  private fun observeConnectivity() {
    connectivityObserver.observe().onEach { status ->
      _hasInternet.value = status == ConnectivityObserver.Status.Available
    }.launchIn(viewModelScope)
  }
}
