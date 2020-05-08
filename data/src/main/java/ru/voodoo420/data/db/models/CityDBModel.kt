package ru.voodoo420.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityDBModel(@PrimaryKey val id: Int, val name: String, val lat: Float, val lon: Float)