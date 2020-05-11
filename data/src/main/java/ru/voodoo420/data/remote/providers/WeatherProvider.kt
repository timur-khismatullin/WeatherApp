package ru.voodoo420.data.remote.providers

import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.entities.Result

interface WeatherProvider {

    suspend fun getFiveDaysForecast(lat: Float, lon: Float): List<ForecastUnit>
    suspend fun getCurrentWeather(lat: Float, lon: Float): CityCurrentWeather
    suspend fun getCurrentWeatherByCity(name: String): Result
}