package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.usecases.AddCityUseCase

class AddCityViewModel(private val addCityUseCase: AddCityUseCase) : ViewModel(){

    val viewState = MutableLiveData<City>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
           addCityUseCase.getCityName().collect {
               withContext(Dispatchers.Main){
                   viewState.value = it
               }
           }
        }
    }

    suspend fun clearLocation(){
        addCityUseCase.clearCity()
    }

    suspend fun setCoordByName(city: String){
        addCityUseCase.execute(city)
    }
    
    suspend fun addCityToDb(cityName: String){
        addCityUseCase.addCity(cityName)
    }
}