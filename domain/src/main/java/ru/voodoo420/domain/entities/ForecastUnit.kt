package ru.voodoo420.domain.entities

data class ForecastUnit(
    val dateTime: String?,
    val temperature: Float,
    val icon: String?,
    val description: String?,
    val humidity: Int,
    val feelsLike: Float,
    val min: Float,
    val max: Float,
    val wind: Float,
    val pressure: Int
)