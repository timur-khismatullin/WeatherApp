package ru.voodoo420.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.usecases.AddCityUseCase

class AddCityViewModel(private val addCityUseCase: AddCityUseCase) : ViewModel(){

    val viewState = MutableLiveData<City>()

    init {
        viewModelScope.launch(Dispatchers.Main) {
           addCityUseCase.getCityName().collect {
               viewState.value = it
           }
        }
    }

    suspend fun cleatLocation(){
        addCityUseCase.clearCity()
    }

    suspend fun setCoordByName(city: String){
        addCityUseCase.execute(city)
    }


}