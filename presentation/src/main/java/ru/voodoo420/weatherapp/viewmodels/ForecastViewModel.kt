package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.usecases.GetForecastByCoordUseCase
import ru.voodoo420.domain.usecases.GetObservableCoordFromDbUseCase

class ForecastViewModel(
    getForecast: GetForecastByCoordUseCase,
    getObservableCoordFromDbUseCase: GetObservableCoordFromDbUseCase
) : ViewModel() {

    val viewState = MutableLiveData<List<ForecastUnit>>()
    private val coord = MutableLiveData<Coord>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getObservableCoordFromDbUseCase.getCoord()
                .collect {
                    withContext(Dispatchers.Main) {
                        coord.value = Coord(it.lat, it.lon)
                        viewState.value =
                            getForecast.execute(Coord(coord.value!!.lat, coord.value!!.lon))
                    }
                }
        }
    }
}