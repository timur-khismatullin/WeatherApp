package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.usecases.GetForecastByCoordUseCase

class ForecastViewModel(getForecast: GetForecastByCoordUseCase) : ViewModel(){

    val viewState = MutableLiveData<List<ForecastUnit>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                viewState.value = getForecast.execute()
            }
        }
    }
}