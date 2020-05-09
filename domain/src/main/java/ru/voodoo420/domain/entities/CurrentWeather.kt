package ru.voodoo420.domain.entities

data class CurrentWeather(
    val city: String,
    val temperature: Float,
    val icon: String,
    val humidity: Int,
    val wind: Float,
    val minTemp: Float,
    val maxTemp: Float,
    val feels: Float,
    val description: String
)