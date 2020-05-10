package ru.voodoo420.data.converters

import ru.voodoo420.data.db.models.CityDBModel
import ru.voodoo420.domain.entities.City

interface FromEntitiesToDbConverter {
    fun convertCity(city: City): CityDBModel
}