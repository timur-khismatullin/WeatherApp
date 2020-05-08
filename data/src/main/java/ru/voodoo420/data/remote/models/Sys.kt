package ru.voodoo420.data.remote.models

data class Sys(
    val pod: String,
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)