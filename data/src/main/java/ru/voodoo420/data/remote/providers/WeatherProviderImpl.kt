package ru.voodoo420.data.remote.providers

import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.remote.services.OpenWeatherMapApiService
import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.entities.Result

class WeatherProviderImpl(private val converter: FromApiToEntitiesConverter) : WeatherProvider {

    private val apiKey = "7b140ec79d4dddefd015ef903035d1f8"
    private val units = "metric"

    override suspend fun getFiveDaysForecast(lat: Float, lon: Float): List<ForecastUnit> {
        return converter.convertForecast(OpenWeatherMapApiService.create().loadForecast(lat, lon, apiKey, units))
    }

    override suspend fun getCurrentWeather(lat: Float, lon: Float): CityCurrentWeather {
        return converter.convertCityCurrentWeather(OpenWeatherMapApiService.create().loadCurrentWeather(lat, lon, apiKey, units))
    }

    override suspend fun getCurrentWeatherByCity(name: String): Result {
        val response = OpenWeatherMapApiService.create().loadWeatherByCity(name, apiKey, units)
        if (response.isSuccessful) {
            val converted = converter.convertCityCurrentWeather(response.body()!!)
            return Result(response.code(), response.message(), converted)
        }
        return Result(response.code(), response.message(), null)
    }
}