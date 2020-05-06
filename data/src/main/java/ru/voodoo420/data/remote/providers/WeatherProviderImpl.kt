package ru.voodoo420.data.remote.providers

import ru.voodoo420.data.models.CurrentWeatherModel
import ru.voodoo420.data.models.FiveDaysForecast
import ru.voodoo420.data.remote.services.OpenWeatherMapApiService

class WeatherProviderImpl : WeatherProvider {

    private val apiKey = "7b140ec79d4dddefd015ef903035d1f8"
    private val units = "metric"

    override suspend fun getFiveDaysForecast(): FiveDaysForecast {
        return OpenWeatherMapApiService.create().loadForecast(55.7887f, 49.1221f, apiKey, units)
    }

    override suspend fun getCurrentWeather(): CurrentWeatherModel {
        return OpenWeatherMapApiService.create().loadCurrentWeather(55.7887f, 49.1221f, apiKey, units)
    }
}