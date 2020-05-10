package ru.voodoo420.data.converters

import ru.voodoo420.data.db.models.CityDBModel
import ru.voodoo420.domain.entities.City

class FromEntitiesToDbConverterImpl : FromEntitiesToDbConverter {
    override fun convertCity(city: City): CityDBModel = with(city) {
        CityDBModel(id, name, country, coord.lat, coord.lon)
    }
}