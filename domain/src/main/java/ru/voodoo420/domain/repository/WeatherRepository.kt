package ru.voodoo420.domain.repository

import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit

interface WeatherRepository {
    suspend fun loadForecast(): List<ForecastUnit>
    suspend fun loadCurrentWeather(): CurrentWeather
}