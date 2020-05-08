package ru.voodoo420.data.remote.providers

import ru.voodoo420.data.remote.models.CurrentWeatherModel
import ru.voodoo420.data.remote.models.FiveDaysForecast
import ru.voodoo420.data.remote.services.OpenWeatherMapApiService

class WeatherProviderImpl : WeatherProvider {
    private val apiKey = "7b140ec79d4dddefd015ef903035d1f8"
    private val units = "metric"

    override suspend fun getFiveDaysForecast(lat: Float, lon: Float): FiveDaysForecast {
        return OpenWeatherMapApiService.create().loadForecast(lat, lon, apiKey, units)
    }

    override suspend fun getCurrentWeather(lat: Float, lon: Float): CurrentWeatherModel {
        return OpenWeatherMapApiService.create().loadCurrentWeather(lat, lon, apiKey, units)
    }
}