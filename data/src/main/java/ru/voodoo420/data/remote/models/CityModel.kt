package ru.voodoo420.data.remote.models

data class CityModel(
    val coordModel: CoordModel,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)