package ru.voodoo420.data.repositories

import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.entities.Result
import ru.voodoo420.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(private val weatherProvider: WeatherProvider) : WeatherRepository {

    override suspend fun loadForecast(coord: Coord): List<ForecastUnit> {
        return weatherProvider.getFiveDaysForecast(coord.lat, coord.lon)
    }

    override suspend fun loadCurrentWeather(coord: Coord): CityCurrentWeather {
        return weatherProvider.getCurrentWeather(coord.lat, coord.lon)
    }

    override suspend fun loadWeatherByCityName(city: String): Result {
        return weatherProvider.getCurrentWeatherByCity(city)
    }
}