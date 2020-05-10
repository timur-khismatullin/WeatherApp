package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.repositories.CitiesRepository

class DeleteCityUseCase(private val citiesRepository: CitiesRepository) {
    suspend fun execute(city: City){
        citiesRepository.deleteCity(city)
    }
}