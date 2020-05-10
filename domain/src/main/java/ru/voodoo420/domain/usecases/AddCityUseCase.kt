package ru.voodoo420.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository

class AddCityUseCase(
    private val citiesRepository: CitiesRepository,
    private val weatherRepository: WeatherRepository
) {

    fun getObservableCurrentLocationFromDb(): Flow<City>{
        citiesRepository.getCurrentLocation()
        return citiesRepository.getCurrentLocation()
    }

    suspend fun clearCurrentLocationInDb(){
        citiesRepository.clearCurrentLocation()
    }

    suspend fun addCityToDb(cityName: String){
        val cityWeather = weatherRepository.loadWeatherByCityName(cityName)
        val city = cityWeather.city.apply { City(name, country, id, Coord( coord.lat, coord.lon)) }
        citiesRepository.addCity(city)
    }

}