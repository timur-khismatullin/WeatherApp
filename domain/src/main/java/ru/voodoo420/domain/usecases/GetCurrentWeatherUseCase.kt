package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.repositories.WeatherRepository

class GetCurrentWeatherUseCase(private val repository: WeatherRepository){

    suspend fun byCoord(coord: Coord): CurrentWeather {
        return repository.loadCurrentWeather(coord).weather
    }

    suspend fun byName(cityName: String): CityCurrentWeather {
        return repository.loadWeatherByCityName(cityName)
    }
}