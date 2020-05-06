package ru.voodoo420.weatherapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.converters.FromApiToEntitiesConverterImpl
import ru.voodoo420.data.models.CurrentWeatherModel
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.data.remote.providers.WeatherProviderImpl
import ru.voodoo420.data.repository.WeatherRepositoryImpl
import ru.voodoo420.domain.entities.CurrentWeather
import ru.voodoo420.domain.repository.WeatherRepository
import ru.voodoo420.domain.usecases.GetCurrentWeatherByCoordUseCase
import ru.voodoo420.domain.usecases.GetForecastByCoordUseCase
import ru.voodoo420.weatherapp.viewmodels.CurrentWeatherViewModel
import ru.voodoo420.weatherapp.viewmodels.ForecastViewModel

val repositoryModule = module {
    single<WeatherProvider> {WeatherProviderImpl()}
    single<FromApiToEntitiesConverter> {FromApiToEntitiesConverterImpl()}
    single<WeatherRepository> {WeatherRepositoryImpl(get(), get())}
}

val useCasesModule = module {
    single { GetForecastByCoordUseCase(get()) }
    single { GetCurrentWeatherByCoordUseCase(get()) }
}

val forecastModule = module {
    viewModel { ForecastViewModel(get()) }
}

val currentWeatherModule = module {
    viewModel { CurrentWeatherViewModel(get()) }
}