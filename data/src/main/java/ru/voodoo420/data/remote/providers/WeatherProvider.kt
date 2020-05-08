package ru.voodoo420.data.remote.providers

import ru.voodoo420.data.remote.models.CurrentWeatherModel
import ru.voodoo420.data.remote.models.FiveDaysForecast

interface WeatherProvider {

    suspend fun getFiveDaysForecast(lat: Float, lon: Float): FiveDaysForecast
    suspend fun getCurrentWeather(lat: Float, lon: Float): CurrentWeatherModel
}