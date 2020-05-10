package ru.voodoo420.data.repositories

import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherProvider: WeatherProvider,
    private val converter: FromApiToEntitiesConverter //todo convert in provider
) : WeatherRepository {

    override suspend fun loadForecast(coord: Coord): List<ForecastUnit> {
        return converter.convertForecast(weatherProvider.getFiveDaysForecast(coord.lat, coord.lon))
    }

    override suspend fun loadCurrentWeather(coord: Coord): CityCurrentWeather {
        return converter.convertCityCurrentWeather(
            weatherProvider.getCurrentWeather(
                coord.lat, coord.lon
            )
        )
    }

    override suspend fun loadWeatherByCityName(city: String): CityCurrentWeather {
        return converter.convertCityCurrentWeather(weatherProvider.getCurrentWeatherByCity(city))
    }
}