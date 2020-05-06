package ru.voodoo420.data.repositories

import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherProvider: WeatherProvider,
    private val converter: FromApiToEntitiesConverter
) : WeatherRepository {

    override suspend fun loadForecast(lat: Float, lon: Float): List<ForecastUnit> {
        return converter.convertForecast(weatherProvider.getFiveDaysForecast(lat, lon))
    }

    override suspend fun loadCurrentWeather(lat: Float, lon: Float): CurrentWeather {
        return converter.convertCurrentWeather(weatherProvider.getCurrentWeather(lat, lon))
    }
}