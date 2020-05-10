package ru.voodoo420.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.Coord

interface CitiesRepository {
    suspend fun loadCities(): Flow<List<City>>
    suspend fun setMainCoord(coord: Coord)
    suspend fun setCurrentCoord(coord: Coord, cityName: String)
    suspend fun setStartData(coord: Coord)
    fun getMainCoord(): Flow<Coord>
    fun getCurrentLocation(): Flow<City>
    suspend fun clearCurrentLocation()
    suspend fun addCity(city: City)
    suspend fun deleteCity(city: City)
}