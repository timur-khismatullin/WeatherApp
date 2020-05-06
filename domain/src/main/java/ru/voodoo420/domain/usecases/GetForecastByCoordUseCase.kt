package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repository.WeatherRepository

class GetForecastByCoordUseCase(private val repository: WeatherRepository){

    suspend fun execute() : List<ForecastUnit>{
        return repository.loadForecast()
    }
}