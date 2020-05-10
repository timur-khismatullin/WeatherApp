package ru.voodoo420.data.remote.models

data class FiveDaysForecast(
    val city: CityModel,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)