package ru.voodoo420.data.repositories

import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherProvider: WeatherProvider,
    private val converter: FromApiToEntitiesConverter
) : WeatherRepository {

    override suspend fun loadForecast(coord: Coord): List<ForecastUnit> {
        return converter.convertForecast(weatherProvider.getFiveDaysForecast(coord.lat, coord.lon))
    }

    override suspend fun loadCurrentWeather(coord: Coord): CurrentWeather {
        return converter.convertCurrentWeather(
            weatherProvider.getCurrentWeather(
                coord.lat,
                coord.lon
            )
        )
    }
}