package ru.voodoo420.weatherapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.voodoo420.weatherapp.di.modules
import timber.log.Timber

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}