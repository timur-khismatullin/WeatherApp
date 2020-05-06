package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repositories.WeatherRepository

class GetForecastByCoordUseCase(private val repository: WeatherRepository, private val lat: Float,
                                private val lon: Float){

    suspend fun execute() : List<ForecastUnit>{
        return repository.loadForecast(lat, lon)
    }
}