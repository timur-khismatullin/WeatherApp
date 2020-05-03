package ru.voodoo420.data.models

data class FiveDaysForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)