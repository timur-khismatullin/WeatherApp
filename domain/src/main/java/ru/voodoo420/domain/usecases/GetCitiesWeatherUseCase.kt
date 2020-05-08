package ru.voodoo420.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository

class GetCitiesWeatherUseCase(
    private val citiesRepository: CitiesRepository,
    private val weatherRepository: WeatherRepository
) {

    private suspend fun getCurrentWeather(coord: Coord): CurrentWeather {
        return weatherRepository.loadCurrentWeather(coord)
    }

    fun getCitiesWeather(): Flow<List<CityCurrentWeather>> = flow {
        citiesRepository.loadCities().collect {
            val cityList = mutableListOf<CityCurrentWeather>()
            //todo optimize: parallel requests
            it.forEach { city ->
                cityList.add(CityCurrentWeather(city, getCurrentWeather(city.coord)))
            }
            emit(cityList)
        }
    }
}