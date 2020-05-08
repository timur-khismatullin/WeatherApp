package ru.voodoo420.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.voodoo420.data.db.CityList
import ru.voodoo420.data.db.RoomAppDatabase
import ru.voodoo420.data.db.models.UtilValues
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.repositories.CitiesRepository

class CitiesRepositoryImpl(
    private val roomAppDatabase: RoomAppDatabase,
    private val context: Context
) : CitiesRepository {

    override suspend fun loadCities(): Flow<List<City>> {
        return roomAppDatabase.citiesDao().getCities()
            //todo optimize
            .map {
                val cities = mutableListOf<City>()
                it.forEach { city ->
                    city.apply { cities.add(City(name, Coord(lat, lon))) }
                }
                return@map cities
            }
    }

    override fun getUtilValues(): Flow<Coord> {
        return roomAppDatabase.utilValuesDao().getUtilValues().map { Coord(it[0].lat, it[0].lon) }
    }

    override suspend fun setUtilValues(coord: Coord) {
        val utilValues = UtilValues(1, coord.lat, coord.lon, 123, false) //todo
        roomAppDatabase.utilValuesDao().insert(utilValues)
    }

    override suspend fun setStartData(coord: Coord) {
        if (!context.getDatabasePath("WeatherApp.db").exists()) {
            val utilValues = UtilValues(1, coord.lat, coord.lon, 123, false) //todo
            roomAppDatabase.utilValuesDao().insert(utilValues)
            roomAppDatabase.citiesDao().insertList(CityList.cities)
        }
    }
}