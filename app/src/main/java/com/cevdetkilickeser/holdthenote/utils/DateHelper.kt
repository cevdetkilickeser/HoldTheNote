package com.cevdetkilickeser.holdthenote.utils

import java.text.SimpleDateFormat
import java.util.Date

object DateHelper {
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")

    fun getCurrentDate(): String {
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }
}