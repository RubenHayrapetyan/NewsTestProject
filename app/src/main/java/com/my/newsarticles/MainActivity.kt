package com.my.newsarticles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.my.newsarticles.navigation.NavGraph
import com.my.newsarticles.ui.theme.NewsArticlesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      NewsArticlesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
          Box(modifier = Modifier.padding(it)) {
            NavGraph()
          }
        }
      }
    }
  }
}
