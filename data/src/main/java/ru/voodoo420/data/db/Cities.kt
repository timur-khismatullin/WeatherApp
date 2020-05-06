package ru.voodoo420.data.db

import ru.voodoo420.domain.entities.City

object Cities {

    val citiesList = mutableListOf(
        City("Ibiza", 38.906986f, 1.421416f),
        City("Kazan", 55.796391f, 55.796391f),
        City("London", 51.509865f,  -0.118092f),
        City("Madrid", 40.416775f, -3.703790f),
        City("Milan", 45.464664f, 9.188540f),
        City("Moscow", 55.751244f, 37.618423f),
        City("New York", 40.785091f, -73.968285f),
        City("Paris", 48.846870f, 2.337170f),
        City("Tokyo", 35.712223f, 139.771118f)
    )
}