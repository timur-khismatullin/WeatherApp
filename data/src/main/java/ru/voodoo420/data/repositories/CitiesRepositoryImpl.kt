package ru.voodoo420.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.voodoo420.data.converters.FromEntitiesToDbConverter
import ru.voodoo420.data.db.StartCityList
import ru.voodoo420.data.db.RoomAppDatabase
import ru.voodoo420.data.db.models.UtilValues
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.Coord
import ru.voodoo420.domain.repositories.CitiesRepository

class CitiesRepositoryImpl(
    private val roomAppDatabase: RoomAppDatabase,
    private val context: Context,
    private val converter: FromEntitiesToDbConverter //todo convert in a lower level layer
) : CitiesRepository {

    override suspend fun loadCities(): Flow<List<City>> {
        return roomAppDatabase.citiesDao().getCities()
            //todo optimize
            .map {
                val cities = mutableListOf<City>()
                it.forEach { city ->
                    city.apply { cities.add(City(name, county, id, Coord(lat, lon))) }
                }
                return@map cities
            }
    }

    override fun getMainCoord(): Flow<Coord> {
        return roomAppDatabase.utilValuesDao().getUtilValues().map { it[0].run { Coord(lat, lon) } }
    }

    override fun getCurrentLocation(): Flow<City> {   //todo make another table?
        return roomAppDatabase.utilValuesDao().getUtilValues()
            .map { it[1].run { City(city, country, id, Coord(lat, lon)) } }
    }

    override suspend fun clearCurrentLocation() {
        val clear = UtilValues(2, 0f, 0f, "", "")
        roomAppDatabase.utilValuesDao().insert(clear)
    }

    override suspend fun addCity(city: City) {
        roomAppDatabase.citiesDao().insert(converter.convertCity(city))
    }

    override suspend fun deleteCity(city: City) {
        roomAppDatabase.citiesDao().delete(converter.convertCity(city))
    }

    override suspend fun setMainCoord(coord: Coord) {
        val mainCoord = UtilValues(1, coord.lat, coord.lon, "", "")
        roomAppDatabase.utilValuesDao().insert(mainCoord)
    }

    override suspend fun setCurrentCoord(coord: Coord, cityName: String) {
        val utilValues = UtilValues(2, coord.lat, coord.lon, cityName, "")
        roomAppDatabase.utilValuesDao().insert(utilValues)
    }

    override suspend fun setStartData(coord: Coord) {
        if (!context.getDatabasePath("WeatherApp.db").exists()) {
            setMainCoord(coord)
            clearCurrentLocation()
            roomAppDatabase.citiesDao().insertList(StartCityList.cities)
        }
    }
}