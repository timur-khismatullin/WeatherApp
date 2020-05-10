package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository

class SetUtilValuesToDbUseCase(
    private val citiesRepository: CitiesRepository,
    private val weatherRepository: WeatherRepository
) {
    suspend fun setMainCoord(coord: Coord) {
        citiesRepository.setMainCoord(coord)
    }

    suspend fun setStartData(coord: Coord) {
        citiesRepository.setStartData(coord)
    }

    suspend fun setCurrentCoord(coord: Coord){
        citiesRepository.setCurrentCoord(coord, weatherRepository.loadCurrentWeather(coord).city.name)
    }
}