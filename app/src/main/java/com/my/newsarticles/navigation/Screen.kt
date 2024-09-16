package com.my.newsarticles.navigation

import com.my.newsarticles.util.AppConstants

sealed class Screen(val route: String) {
  data object NewsScreen : Screen(AppConstants.ROUTE_NEWS)
  data object NewsDetailsScreen : Screen(AppConstants.ROUTE_NEWS_DETAILS)
}