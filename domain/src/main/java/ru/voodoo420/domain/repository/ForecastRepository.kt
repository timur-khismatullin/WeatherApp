package ru.voodoo420.domain.repository

import ru.voodoo420.domain.entities.ForecastUnit

interface ForecastRepository {

    suspend fun loadForecast(): List<ForecastUnit>
}