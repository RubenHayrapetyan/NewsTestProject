package com.my.newsarticles.navigation

import com.my.newsarticles.util.Constants

sealed class Screen(val route: String) {
  data object NewsScreen : Screen(Constants.ROUTE_NEWS)
  data object NewsDetailsScreen : Screen(Constants.ROUTE_NEWS_DETAILS)
}