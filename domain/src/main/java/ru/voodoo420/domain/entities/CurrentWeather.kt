package ru.voodoo420.domain.entities

data class CurrentWeather(val city: String, val temperature: Double, val icon: String,
                          val humidity: Int, val wind: Double, val minTemp: Double, val maxTemp: Double)