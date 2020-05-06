package ru.voodoo420.domain.repositories

import ru.voodoo420.domain.entities.City

interface CitiesRepository {
    suspend fun loadCities(): List<City>
}