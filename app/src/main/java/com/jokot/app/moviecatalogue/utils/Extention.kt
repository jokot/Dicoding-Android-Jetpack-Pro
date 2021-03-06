package com.jokot.app.moviecatalogue.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun convertDateFormat(dateString: String?): String {
    return if (dateString != null) {
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        val date = formatter.parse(dateString) as Date
        SimpleDateFormat("MMM dd, yyyy").format(date)
    } else {
        "Not available"
    }
}

fun convertRunTimeToDuration(runTime: Int?): String {
    return if (runTime != null) {
        if (runTime < 60) {
            "${runTime}m"
        } else {
            val hour = runTime / 60
            val minute = runTime % 60
            "${hour}h ${minute}m"
        }
    } else {
        "0m"
    }
}