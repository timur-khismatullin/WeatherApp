package ru.voodoo420.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.voodoo420.data.db.models.CityDBModel

@Dao
interface CitiesDao {
    @Query("SELECT * FROM CityDBModel")
    fun getCities(): Flow<List<CityDBModel>>

    @Query("SELECT * FROM CityDBModel")
    fun get(): List<CityDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityDBModel: CityDBModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<CityDBModel>)

    @Update
    suspend fun update(cityDBModel: CityDBModel)

    @Delete
    suspend fun delete(cityDBModel: CityDBModel)
}