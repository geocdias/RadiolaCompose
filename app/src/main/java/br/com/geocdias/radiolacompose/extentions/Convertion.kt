package br.com.geocdias.radiolacompose.extentions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.formatMilliseconds(): String {
    val format = SimpleDateFormat("mm:ss", Locale.getDefault())
    return format.format(Date(this))
}
