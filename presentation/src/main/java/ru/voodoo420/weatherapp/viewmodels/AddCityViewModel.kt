package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.domain.usecases.AddCityUseCase
import ru.voodoo420.domain.usecases.GetCurrentWeatherUseCase

class AddCityViewModel(
    private val addCityUseCase: AddCityUseCase,
    private val getCurrentWeather: GetCurrentWeatherUseCase
) : ViewModel() {

    val observableCoordFromDb = MutableLiveData<City>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            addCityUseCase.getObservableCurrentLocationFromDb().collect {
                withContext(Dispatchers.Main) {
                    observableCoordFromDb.value = it
                }
            }
        }
    }

    suspend fun clearLocation() {
        addCityUseCase.clearCurrentLocationInDb()
    }

    suspend fun addCityToDb(cityName: String) {
        addCityUseCase.addCityToDb(cityName)
    }

    suspend fun getWeather(cityName: String): CityCurrentWeather = getCurrentWeather.byName(cityName)
}