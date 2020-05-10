package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.usecases.GetCurrentWeatherUseCase
import ru.voodoo420.domain.usecases.GetObservableCoordFromDbUseCase
import ru.voodoo420.domain.usecases.SetUtilValuesToDbUseCase

class CurrentWeatherViewModel(
    getCurrentWeather: GetCurrentWeatherUseCase,
    getObservableCoord: GetObservableCoordFromDbUseCase,
    private val setCoordUseCase: SetUtilValuesToDbUseCase
) : ViewModel() {

    private val coord = MutableLiveData<Coord>()
    val viewState = MutableLiveData<CurrentWeather>()

    private suspend fun setStartData(coord: Coord) {
        setCoordUseCase.setStartData(coord)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            setStartData(Coord(55.7887f, 49.1221f))
            getObservableCoord.execute()
                .collect {
                    withContext(Dispatchers.Main) {
                        coord.value = Coord(it.lat, it.lon)
                        viewState.value =
                            getCurrentWeather.execute(Coord(coord.value!!.lat, coord.value!!.lon))
                    }
                }
        }
    }
}