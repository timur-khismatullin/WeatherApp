package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.usecases.GetCitiesWeatherUseCase

class CitiesViewModel(getCities: GetCitiesWeatherUseCase) : ViewModel() {
    val viewState = MutableLiveData<List<Pair<City, CurrentWeather>>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                viewState.value = getCities.getCitiesWeather()
            }
        }
    }
}