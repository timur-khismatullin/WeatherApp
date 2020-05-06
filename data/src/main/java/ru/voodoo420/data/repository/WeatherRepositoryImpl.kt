package ru.voodoo420.data.repository

import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherProvider: WeatherProvider,
                            private val converter: FromApiToEntitiesConverter)
    : WeatherRepository {

    override suspend fun loadForecast(): List<ForecastUnit> {
        return converter.convertForecast(weatherProvider.getFiveDaysForecast())
    }

    override suspend fun loadCurrentWeather(): CurrentWeather {
        return converter.convertCurrentWeather(weatherProvider.getCurrentWeather())
    }
}