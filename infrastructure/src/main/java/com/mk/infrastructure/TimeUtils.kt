package com.mk.infrastructure

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentTime() = formatDateISO(Date())

fun formatDateISO(date: Date) =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(date)

fun formatMsISO(date: Long) =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(Date(date.toLong()))
