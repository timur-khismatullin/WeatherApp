package ru.voodoo420.data.remote.providers

import ru.voodoo420.data.models.CurrentWeatherModel
import ru.voodoo420.data.models.FiveDaysForecast

interface WeatherProvider {

    suspend fun getFiveDaysForecast(): FiveDaysForecast
    suspend fun getCurrentWeather(): CurrentWeatherModel
}