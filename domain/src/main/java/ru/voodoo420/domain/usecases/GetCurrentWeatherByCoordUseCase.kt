package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.repositories.WeatherRepository

class GetCurrentWeatherByCoordUseCase(private val repository: WeatherRepository){

    suspend fun execute(coord: Coord): CurrentWeather {
        return repository.loadCurrentWeather(coord).weather
    }
}