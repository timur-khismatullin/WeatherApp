package ru.voodoo420.data.db

import ru.voodoo420.data.db.models.CityDBModel

object StartCityList {
    val cities = mutableListOf(
        CityDBModel(551487,"Kazan","RU",55.7887f,49.1221f),
        CityDBModel(2643743,"London", "GB",51.509865f,-0.118092f),
        CityDBModel(3117735,"Madrid","ES",40.416775f,-3.703790f),
        CityDBModel(3173435,"Milan", "IT",45.464664f,9.188540f),
        CityDBModel(524901,"Moscow", "RU",55.751244f,37.618423f),
        CityDBModel(5128581,"New York","US",40.785091f,-73.968285f)
    )
}