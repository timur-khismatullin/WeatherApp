package ru.voodoo420.weatherapp.entities.models

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int,
    val pod: String
)