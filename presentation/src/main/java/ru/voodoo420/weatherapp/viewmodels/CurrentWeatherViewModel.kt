package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.usecases.GetCurrentWeatherByCoordUseCase

class CurrentWeatherViewModel(private val getCurrentWeather: GetCurrentWeatherByCoordUseCase) : ViewModel(){

    val viewState = MutableLiveData<CurrentWeather>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                viewState.value = getCurrentWeather.execute()
            }
        }
    }
}