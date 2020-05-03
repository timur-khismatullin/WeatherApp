package ru.voodoo420.data.converters

import ru.voodoo420.data.models.FiveDaysForecast
import ru.voodoo420.domain.entities.ForecastUnit

class ForecastConverterImpl : ForecastConverter {
    override fun fromApiToDomain(model: FiveDaysForecast): List<ForecastUnit>{
        val forecastList = mutableListOf<ForecastUnit>()
        model.list.forEach{
           forecastList.add(ForecastUnit(it.dt_txt, it.main.temp, it.weather[0].icon))
        }
        return forecastList
    }
}