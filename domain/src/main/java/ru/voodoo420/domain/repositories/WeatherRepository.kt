package ru.voodoo420.domain.repositories

import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit

interface WeatherRepository {
    suspend fun loadForecast(coord: Coord): List<ForecastUnit>
    suspend fun loadCurrentWeather(coord: Coord): CurrentWeather
}