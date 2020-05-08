package ru.voodoo420.domain.usecases

import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.repositories.CitiesRepository

class SetUtilValuesToDbUseCase(private val repository: CitiesRepository) {
    suspend fun setCoord(coord: Coord){
        repository.setUtilValues(coord)
    }

    suspend fun setStartData(coord: Coord){
        repository.setStartData(coord)
    }
}