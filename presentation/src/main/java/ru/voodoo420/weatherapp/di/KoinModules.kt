package ru.voodoo420.weatherapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.converters.FromApiToEntitiesConverterImpl
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.data.remote.providers.WeatherProviderImpl
import ru.voodoo420.data.repositories.CitiesRepositoryImpl
import ru.voodoo420.data.repositories.WeatherRepositoryImpl
import ru.voodoo420.domain.entities.Coordinates
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository
import ru.voodoo420.domain.usecases.GetCitiesWeatherUseCase
import ru.voodoo420.domain.usecases.GetCurrentWeatherByCoordUseCase
import ru.voodoo420.domain.usecases.GetForecastByCoordUseCase
import ru.voodoo420.weatherapp.viewmodels.CitiesViewModel
import ru.voodoo420.weatherapp.viewmodels.CurrentWeatherViewModel
import ru.voodoo420.weatherapp.viewmodels.ForecastViewModel

var lat = Coordinates.lat
var lon = Coordinates.lon

val repositoryModule = module {
    single{Coordinates}
    single<WeatherProvider> {WeatherProviderImpl()}
    single<FromApiToEntitiesConverter> {FromApiToEntitiesConverterImpl()}
    single<WeatherRepository> {WeatherRepositoryImpl(get(), get())}
    single<CitiesRepository>{CitiesRepositoryImpl()}
}

val useCasesModule = module {
    single { GetForecastByCoordUseCase(get(), lat, lon) }
    single { GetCurrentWeatherByCoordUseCase(get(), lat, lon) }
    single { GetCitiesWeatherUseCase(get(),get()) }
}

val forecastModule = module {
    viewModel { ForecastViewModel(get()) }
}

val currentWeatherModule = module {
    viewModel { CurrentWeatherViewModel(get()) }
}

val citiesModule = module {
    viewModel { CitiesViewModel(get()) }
}

val modules = listOf(repositoryModule, useCasesModule, forecastModule, currentWeatherModule, citiesModule)