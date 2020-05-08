package ru.voodoo420.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.voodoo420.data.db.dao.CitiesDao
import ru.voodoo420.data.db.dao.UtilValuesDao
import ru.voodoo420.data.db.models.CityDBModel
import ru.voodoo420.data.db.models.UtilValues

@Database(entities = [UtilValues::class, CityDBModel::class], version = 1)
abstract class RoomAppDatabase : RoomDatabase() {

    abstract fun utilValuesDao(): UtilValuesDao
    abstract fun citiesDao(): CitiesDao

    companion object{
        fun buildDatabase(context: Context): RoomAppDatabase = Room.databaseBuilder(
            context, RoomAppDatabase::class.java, "WeatherApp.db")
            .fallbackToDestructiveMigration() //todo don't forget to delete
            .build()
    }
}