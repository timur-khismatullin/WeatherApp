package ru.voodoo420.weatherapp.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.converters.FromApiToEntitiesConverterImpl
import ru.voodoo420.data.db.RoomAppDatabase
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.data.remote.providers.WeatherProviderImpl
import ru.voodoo420.data.repositories.CitiesRepositoryImpl
import ru.voodoo420.data.repositories.WeatherRepositoryImpl
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository
import ru.voodoo420.domain.usecases.*
import ru.voodoo420.weatherapp.viewmodels.CitiesViewModel
import ru.voodoo420.weatherapp.viewmodels.CurrentWeatherViewModel
import ru.voodoo420.weatherapp.viewmodels.ForecastViewModel
import ru.voodoo420.weatherapp.viewmodels.MainActivityViewModel

val repositoriesModule = module {
    single { RoomAppDatabase.buildDatabase(androidContext()) }
    single<WeatherProvider> { WeatherProviderImpl()}
    single<FromApiToEntitiesConverter> { FromApiToEntitiesConverterImpl() }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
    single<CitiesRepository> { CitiesRepositoryImpl(get(),(androidContext())) }
}

val useCasesModule = module {
    single { GetForecastByCoordUseCase(get()) }
    single { GetCurrentWeatherByCoordUseCase(get()) }
    single { GetCitiesWeatherUseCase(get(),get()) }
    single { GetObservableCoordFromDbUseCase(get()) }
    single { SetUtilValuesToDbUseCase(get()) }
}

val viewModelsModule = module {
    viewModel { ForecastViewModel(get(),get()) }
    viewModel { CurrentWeatherViewModel(get(), get(), get()) }
    viewModel { CitiesViewModel(get(), get()) }
    viewModel { MainActivityViewModel(get()) }
}

val modules = listOf(repositoriesModule, useCasesModule, viewModelsModule)