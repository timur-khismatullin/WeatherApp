package ru.voodoo420.data.converters

import ru.voodoo420.data.remote.models.CurrentWeatherModel
import ru.voodoo420.data.remote.models.FiveDaysForecast
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.entities.ForecastUnit

class FromApiToEntitiesConverterImpl : FromApiToEntitiesConverter {

    override fun convertForecast(model: FiveDaysForecast): List<ForecastUnit> {
        val forecastList = mutableListOf<ForecastUnit>()
        model.list.forEach{
            with(it){
                forecastList.add(ForecastUnit(dt_txt, main.temp, weather[0].icon))
            }
        }
        return forecastList
    }

    override fun convertCurrentWeather(model: CurrentWeatherModel): CurrentWeather = with(model) {
        return CurrentWeather(name, main.temp, weather[0].icon, main.humidity, wind.speed, main.temp_min, main.temp_max)
    }
}