package ru.voodoo420.data.models

data class Main(
    val feels_like: Float,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Float,
    val temp_kf: Float,
    val temp_max: Float,
    val temp_min: Float
)