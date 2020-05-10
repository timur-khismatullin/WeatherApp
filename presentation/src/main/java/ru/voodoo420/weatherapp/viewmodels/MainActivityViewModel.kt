package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.usecases.SetUtilValuesToDbUseCase

class MainActivityViewModel(private val setCoordUseCase: SetUtilValuesToDbUseCase) : ViewModel() {

    suspend fun setMainCoord(coord: Coord) {
        setCoordUseCase.setMainCoord(coord)
    }

    suspend fun setCurrentCoord(coord: Coord){
        setCoordUseCase.setCurrentCoord(coord)
    }
}