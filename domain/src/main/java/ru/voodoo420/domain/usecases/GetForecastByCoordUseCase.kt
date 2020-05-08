package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repositories.WeatherRepository

class GetForecastByCoordUseCase(private val repository: WeatherRepository){

    suspend fun execute(coord: Coord) : List<ForecastUnit>{
        return repository.loadForecast(coord)
    }
}