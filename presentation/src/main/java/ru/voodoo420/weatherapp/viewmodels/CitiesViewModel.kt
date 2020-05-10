package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.usecases.GetCitiesWeatherUseCase
import ru.voodoo420.domain.usecases.SetUtilValuesToDbUseCase

class CitiesViewModel(
    getCities: GetCitiesWeatherUseCase,
    private val setCoord: SetUtilValuesToDbUseCase
) : ViewModel() {

    val viewState = MutableLiveData<List<CityCurrentWeather>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCities.execute().collect {
                withContext(Dispatchers.Main) {
                    viewState.value = it
                }
            }
        }
    }

    suspend fun setCoordinates(coord: Coord) {
        setCoord.setMainCoord(coord)
    }
}