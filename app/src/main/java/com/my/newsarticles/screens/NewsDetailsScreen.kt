package com.my.newsarticles.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.my.domain.model.NewsModel
import org.jsoup.Jsoup

@Composable
fun NewsDetailsScreen(newsModel: NewsModel) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    val title: String = newsModel.webTitle
    if (title.isNotEmpty()) {
      Text(
        text = title,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth(),
      )
      Spacer(modifier = Modifier.height(16.dp))
    }

    val imageUrl = newsModel.thumbnailUrl
    if (imageUrl.isNotEmpty()) {
      AsyncImage(
        model = imageUrl,
        contentDescription = imageUrl,
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp),
      )
      Spacer(modifier = Modifier.height(16.dp))
    }

    val dateAndTime = newsModel.webPublicationDate
    if (dateAndTime.isNotEmpty()) {
      Text(
        text = dateAndTime,
        fontStyle = FontStyle.Italic,
        color = Color.Red,
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(16.dp))
    }

    val bodyText = newsModel.body
    if (bodyText.isNotEmpty()) {
      val convertedBodyText = Jsoup.parse(bodyText).text()

      Box(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
      ) {
        Text(
          text = convertedBodyText,
          fontSize = 16.sp,
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  }
}