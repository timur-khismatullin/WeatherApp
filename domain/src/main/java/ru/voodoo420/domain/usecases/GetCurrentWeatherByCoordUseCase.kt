package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.repository.WeatherRepository

class GetCurrentWeatherByCoordUseCase(private val repository: WeatherRepository){

    suspend fun execute() : CurrentWeather{
        return repository.loadCurrentWeather()
    }
}