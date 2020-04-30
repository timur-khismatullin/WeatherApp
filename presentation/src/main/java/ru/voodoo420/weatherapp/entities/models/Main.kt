package ru.voodoo420.weatherapp.entities.models

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val grnd_level: Int,
    val sea_level: Int,
    val temp_kf: Double

)