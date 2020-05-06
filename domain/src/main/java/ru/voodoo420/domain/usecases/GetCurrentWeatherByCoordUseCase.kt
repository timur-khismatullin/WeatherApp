package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.repositories.WeatherRepository

class GetCurrentWeatherByCoordUseCase(private val repository: WeatherRepository, private val lat: Float,
                                      private val lon: Float){

    suspend fun execute(): CurrentWeather {
        return repository.loadCurrentWeather(lat, lon)
    }
}