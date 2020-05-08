package ru.voodoo420.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.Coord

interface CitiesRepository {
    suspend fun loadCities(): Flow<List<City>>
    suspend fun setUtilValues(coord: Coord)
    suspend fun setStartData(coord: Coord)
    fun getUtilValues(): Flow<Coord>
}