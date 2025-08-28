package com.swancodes.travelapp.data.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

data class Trip(
    val id: String,
    val title: String,
    val imageUrl: String? = null,
    val location: String,
    val startDate: String,
    val endDate: String,
    val description: String,
    val category: String = "All Trips"
) {
    val days: Long
        get() = try {
            val formatter = DateTimeFormatter.ofPattern("d['st']['nd']['rd']['th'] MMMM yyyy", Locale.ENGLISH)
            val start = LocalDate.parse(startDate, formatter)
            val end = LocalDate.parse(endDate, formatter)
            ChronoUnit.DAYS.between(start, end) + 1
        } catch (e: Exception) {
            0L
        }
}