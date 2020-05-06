package ru.voodoo420.data.repositories

import ru.voodoo420.data.db.Cities
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.repositories.CitiesRepository

class CitiesRepositoryImpl : CitiesRepository {
    override suspend fun loadCities(): List<City> {
        return Cities.citiesList
    }
}