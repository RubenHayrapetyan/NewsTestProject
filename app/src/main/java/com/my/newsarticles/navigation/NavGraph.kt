package com.my.newsarticles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.my.domain.model.NewsModel
import com.my.newsarticles.screens.NewsDetailsScreen
import com.my.newsarticles.screens.NewsScreen
import com.my.newsarticles.util.AppConstants
import com.my.presentation.viewmodel.NewsViewModel

@Composable
fun NavGraph() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Screen.NewsScreen.route
  ) {
    composable(route = Screen.NewsScreen.route) {
      val viewModel = hiltViewModel<NewsViewModel>()
      val newsPagingItems = viewModel.newsPagingFlow.collectAsLazyPagingItems()
      val hasInternet by viewModel.hasInternet.collectAsState()
      NewsScreen(
        navController = navController,
        isInternetAvailable = hasInternet,
        newsPagingItems = newsPagingItems,
        onSearchQueryChange = {
          viewModel.searchNews(query = it)
        })
    }
    composable(route = Screen.NewsDetailsScreen.route) {
      val newsEntity = navController.previousBackStackEntry?.savedStateHandle?.get<NewsModel>(
        AppConstants.ROUTE_NEWS_DETAILS)
      newsEntity?.let {
        NewsDetailsScreen(newsModel = it)
      }
    }
  }
}