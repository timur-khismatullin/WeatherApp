package ru.voodoo420.data.repository

import ru.voodoo420.data.converters.ForecastConverter
import ru.voodoo420.data.remote.providers.ForecastProvider
import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repository.ForecastRepository

class ForecastRepositoryImpl(private val forecastProvider: ForecastProvider, private val forecastConverter: ForecastConverter) : ForecastRepository {

    override suspend fun loadForecast(): List<ForecastUnit> {
        return forecastConverter.fromApiToDomain(forecastProvider.getFiveDaysForecast())
    }
}