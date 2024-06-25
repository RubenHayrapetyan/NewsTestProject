package com.my.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.toDDmmYYYYhhmm(): String {
  val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
  inputFormat.timeZone = TimeZone.getTimeZone("UTC")

  val outputFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
  val date: Date? = inputFormat.parse(this)

  return if (date != null) {
    outputFormat.format(date)
  } else {
    ""
  }
}