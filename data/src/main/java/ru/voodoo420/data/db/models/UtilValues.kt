package ru.voodoo420.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UtilValues(
    @PrimaryKey val id: Int,
    val lat: Float,
    val lon: Float,
    val city: String
)