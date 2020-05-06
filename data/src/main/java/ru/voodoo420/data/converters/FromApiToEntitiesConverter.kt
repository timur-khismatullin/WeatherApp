package ru.voodoo420.data.converters

import ru.voodoo420.data.models.CurrentWeatherModel
import ru.voodoo420.data.models.FiveDaysForecast
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit

interface FromApiToEntitiesConverter {
    fun convertForecast(model: FiveDaysForecast): List<ForecastUnit>
    fun convertCurrentWeather(model: CurrentWeatherModel): CurrentWeather
}