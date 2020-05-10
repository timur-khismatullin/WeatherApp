package ru.voodoo420.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository

class AddCityUseCase(
    private val citiesRepository: CitiesRepository,
    private val weatherRepository: WeatherRepository
) {

    fun getCityName(): Flow<City>{
        citiesRepository.getCurrentLocation()
        return citiesRepository.getCurrentLocation()
    }

    suspend fun clearCity(){
        citiesRepository.clearCurrentLocation()
    }

    suspend fun execute(city: String){
        citiesRepository.setMainCoord(weatherRepository.loadWeatherByCityName(city).city.coord)
    }
}