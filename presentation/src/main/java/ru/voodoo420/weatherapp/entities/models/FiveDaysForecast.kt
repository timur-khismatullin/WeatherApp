package ru.voodoo420.weatherapp.entities.models

data class FiveDaysForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)