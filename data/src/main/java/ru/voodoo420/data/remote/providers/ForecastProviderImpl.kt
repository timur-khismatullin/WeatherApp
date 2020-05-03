package ru.voodoo420.data.remote.providers

import ru.voodoo420.data.models.FiveDaysForecast
import ru.voodoo420.data.remote.services.OpenWeatherMapApiService

class ForecastProviderImpl : ForecastProvider {

    override suspend fun getFiveDaysForecast(): FiveDaysForecast {
        return OpenWeatherMapApiService.create().loadForecast(55.7887f, 49.1221f, "7b140ec79d4dddefd015ef903035d1f8", "metric")
    }
}