package ru.voodoo420.weatherapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.voodoo420.weatherapp.di.currentWeatherModule
import ru.voodoo420.weatherapp.di.forecastModule
import ru.voodoo420.weatherapp.di.repositoryModule
import ru.voodoo420.weatherapp.di.useCasesModule
import timber.log.Timber

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, useCasesModule, forecastModule, currentWeatherModule ))
        }
    }
}