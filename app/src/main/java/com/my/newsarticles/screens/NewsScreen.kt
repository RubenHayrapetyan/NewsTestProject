package com.my.newsarticles.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.my.domain.model.NewsModel
import com.my.newsarticles.R
import com.my.newsarticles.util.AppConstants
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
  navController: NavHostController,
  isInternetAvailable: Boolean,
  newsPagingItems: LazyPagingItems<NewsModel>,
  onSearchQueryChange: (String) -> Unit
) {
  Column(
    Modifier
      .fillMaxSize()
      .padding(top = 20.dp, start = 16.dp, end = 16.dp)
  ) {
    val listState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    InternetStatus(isInternetAvailable)

    Spacer(modifier = Modifier.height(16.dp))

    SearchBar {
      if (isInternetAvailable) {
        onSearchQueryChange(it)
        coroutineScope.launch {
          listState.scrollToItem(0)
        }
      }
    }
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp)
    ) {
      NewsList(listState = listState, newsPagingItems = newsPagingItems) { newsModel ->
        navController.currentBackStackEntry?.savedStateHandle?.set(
          key = AppConstants.ROUTE_NEWS_DETAILS,
          value = newsModel
        )
        navController.navigate(AppConstants.ROUTE_NEWS_DETAILS)
      }
    }
  }
}

@Composable
private fun InternetStatus(isInternetAvailable: Boolean) {
  val internetStatusText = if (isInternetAvailable) {
    buildAnnotatedString {
      append(stringResource(id = R.string.connection_is))
      append(" ")
      withStyle(style = SpanStyle(color = colorResource(id = R.color.green))) {
        append(stringResource(id = R.string.available))
      }
    }
  } else {
    buildAnnotatedString {
      append(stringResource(id = R.string.connection_is))
      append(" ")
      withStyle(style = SpanStyle(color = colorResource(id = R.color.red))) {
        append(stringResource(id = R.string.unavailable))
      }
    }
  }

  Text(text = internetStatusText)
}

@Composable
private fun NewsList(
  listState: LazyListState,
  newsPagingItems: LazyPagingItems<NewsModel>,
  onNewsClick: (NewsModel) -> Unit
) {
  LazyColumn(
    state = listState,
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    items(newsPagingItems.itemCount) { index ->
      newsPagingItems[index]?.let { newsModel ->
        NewsItem(
          newsModel = newsModel,
          modifier = Modifier.fillMaxWidth(),
          onNewsClick = { news ->
            onNewsClick(news)
          }
        )
      }
    }
    newsPagingItems.apply {
      when {
        loadState.refresh is LoadState.Loading -> {
          item { CircularProgressIndicator() }
        }

        loadState.append is LoadState.Loading -> {
          item {
            Text(text = stringResource(R.string.loading_next_item))
          }
        }

        loadState.append is LoadState.Error -> {
          val error = newsPagingItems.loadState.append as LoadState.Error
          item {
            ErrorMessage(
              modifier = Modifier.fillMaxWidth(),
              message = error.error.localizedMessage,
              onClickRetry = { retry() })
          }
        }

        loadState.refresh is LoadState.Error -> {
          val error = newsPagingItems.loadState.refresh as LoadState.Error
          item {
            ErrorMessage(
              modifier = Modifier.fillMaxWidth(),
              message = error.error.localizedMessage,
              onClickRetry = { retry() })
          }
        }
      }
    }
  }
}

@Composable
private fun NewsItem(
  newsModel: NewsModel,
  modifier: Modifier = Modifier,
  onNewsClick: (NewsModel) -> Unit
) {
  Card(
    modifier = modifier.clickable {
      onNewsClick(newsModel)
    },
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)
        .padding(16.dp)
    ) {
      val imageUrl = newsModel.thumbnailUrl
      if (imageUrl.isNotEmpty()) {
        AsyncImage(
          model = newsModel.thumbnailUrl,
          contentDescription = newsModel.thumbnailUrl,
          modifier = Modifier
            .weight(2f)
            .height(150.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
      }

      Column(
        modifier = Modifier
          .weight(3f)
          .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
      ) {
        Text(
          text = newsModel.webTitle,
          style = MaterialTheme.typography.labelLarge,
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        val dateAndTime = newsModel.webPublicationDate
        if (dateAndTime.isNotEmpty()) {
          Text(
            text = dateAndTime,
            fontStyle = FontStyle.Italic,
            color = Color.Red,
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}

@Composable
private fun ErrorMessage(
  message: String?,
  modifier: Modifier = Modifier,
  onClickRetry: () -> Unit
) {
  val errorText = stringResource(id = R.string.error_something_went_wrong)

  Column(modifier = modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
      text = message ?: errorText,
      color = colorResource(id = R.color.red),
      modifier = Modifier.fillMaxWidth(),
      textAlign = TextAlign.Center,
      maxLines = 6
    )
    OutlinedButton(onClick = onClickRetry) {
      Text(text = stringResource(id = R.string.btn_retry))
    }
  }
}

@Composable
private fun SearchBar(
  onQueryChange: (String) -> Unit
) {
  var searchQuery by rememberSaveable { mutableStateOf("") }
  val focusManager = LocalFocusManager.current
  val keyboardController = LocalSoftwareKeyboardController.current

  OutlinedTextField(
    value = searchQuery,
    onValueChange = {
      searchQuery = it
      onQueryChange(searchQuery)
    },
    label = { Text(stringResource(id = R.string.lable_search)) },
    modifier = Modifier.fillMaxWidth(),
    keyboardOptions = KeyboardOptions.Default.copy(
      imeAction = ImeAction.Search
    ),
    keyboardActions = KeyboardActions(
      onSearch = {
        keyboardController?.hide()
        focusManager.clearFocus()
      }
    )
  )
}