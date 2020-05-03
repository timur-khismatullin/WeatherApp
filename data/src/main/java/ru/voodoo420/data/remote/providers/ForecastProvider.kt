package ru.voodoo420.data.remote.providers

import ru.voodoo420.data.models.FiveDaysForecast

interface ForecastProvider {

    suspend fun getFiveDaysForecast(): FiveDaysForecast
}