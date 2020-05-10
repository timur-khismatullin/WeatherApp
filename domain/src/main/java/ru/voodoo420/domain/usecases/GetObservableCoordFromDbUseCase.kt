package ru.voodoo420.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.repositories.CitiesRepository

class GetObservableCoordFromDbUseCase(private val citiesRepository: CitiesRepository) {

   fun execute(): Flow<Coord> = citiesRepository.getMainCoord()
}