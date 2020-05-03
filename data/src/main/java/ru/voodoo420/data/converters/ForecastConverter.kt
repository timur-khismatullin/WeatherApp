package ru.voodoo420.data.converters

import ru.voodoo420.data.models.FiveDaysForecast
import ru.voodoo420.domain.entities.ForecastUnit

interface ForecastConverter {
    fun fromApiToDomain(model: FiveDaysForecast): List<ForecastUnit>
}