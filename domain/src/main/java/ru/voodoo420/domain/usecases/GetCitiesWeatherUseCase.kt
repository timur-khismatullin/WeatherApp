package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository

class GetCitiesWeatherUseCase(private val citiesRepository: CitiesRepository, private val weatherRepository: WeatherRepository) {

    private suspend fun getCities(): List<City>{
        return citiesRepository.loadCities()
    }

    private suspend fun getCurrentWeather(lat: Float, lon: Float) : CurrentWeather {
        return weatherRepository.loadCurrentWeather(lat, lon)
    }

    suspend fun getCitiesWeather(): List<Pair<City, CurrentWeather>>{
        val citiesWeatherList = mutableListOf<Pair<City, CurrentWeather>>()
        val cities = getCities()
        for (i in cities){
            val lat = i.lat
            val lon = i.lon
            citiesWeatherList.add(Pair(i, getCurrentWeather(lat, lon)))
        }
        return citiesWeatherList
    }
}