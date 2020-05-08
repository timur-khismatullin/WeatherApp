package ru.voodoo420.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.voodoo420.data.db.models.UtilValues

@Dao
interface UtilValuesDao {
    @Query("SELECT * FROM UtilValues")
    fun getUtilValues(): Flow<List<UtilValues>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(values: UtilValues)

    @Update
    suspend fun update(values: UtilValues)

    @Delete
    suspend fun delete(values: UtilValues)
}