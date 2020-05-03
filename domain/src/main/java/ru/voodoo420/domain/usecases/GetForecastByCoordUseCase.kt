package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repository.ForecastRepository

class GetForecastByCoordUseCase(private val forecastRepository: ForecastRepository){

    suspend fun execute() : List<ForecastUnit>{
        return forecastRepository.loadForecast()
    }
}