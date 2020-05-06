package ru.voodoo420.domain.repositories

import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit

interface WeatherRepository {
    suspend fun loadForecast(lat: Float, lon: Float): List<ForecastUnit>
    suspend fun loadCurrentWeather(lat: Float, lon: Float): CurrentWeather
}