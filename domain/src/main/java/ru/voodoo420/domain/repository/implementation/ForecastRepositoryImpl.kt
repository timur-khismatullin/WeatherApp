package ru.voodoo420.domain.repository.implementation

import ru.voodoo420.domain.entities.ForecastUnit
import ru.voodoo420.domain.repository.ForecastRepository

class ForecastRepositoryImpl : ForecastRepository {

    override suspend fun loadForecast(): List<ForecastUnit> {
        Thread.sleep(1000)
        return listOf(
            ForecastUnit(
                "today",
                12.0,
                "123"
            ),
            ForecastUnit(
                "tommorow",
                15.0,
                "123"
            )
        )
    }
}