package ru.voodoo420.data.remote.models

data class FiveDaysForecast(
    val cityModel: CityModel,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)