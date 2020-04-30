package ru.voodoo420.weatherapp.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repository.implementation.ForecastRepositoryImpl

class ForecastViewModel : ViewModel(){

    val viewState = MutableLiveData<List<ForecastUnit>>()
    private val forecastRepository = ForecastRepositoryImpl()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                viewState.value = forecastRepository.loadForecast()

            }
        }
    }
}