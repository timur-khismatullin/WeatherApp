package ru.voodoo420.domain.repositories

import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.entities.Result

interface WeatherRepository {
    suspend fun loadForecast(coord: Coord): List<ForecastUnit>
    suspend fun loadCurrentWeather(coord: Coord): CityCurrentWeather
    suspend fun loadWeatherByCityName(city: String): Result
}