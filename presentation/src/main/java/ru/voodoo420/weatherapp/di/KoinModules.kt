package ru.voodoo420.weatherapp.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.voodoo420.data.converters.FromApiToEntitiesConverter
import ru.voodoo420.data.converters.FromApiToEntitiesConverterImpl
import ru.voodoo420.data.converters.FromEntitiesToDbConverter
import ru.voodoo420.data.converters.FromEntitiesToDbConverterImpl
import ru.voodoo420.data.db.RoomAppDatabase
import ru.voodoo420.data.remote.providers.WeatherProvider
import ru.voodoo420.data.remote.providers.WeatherProviderImpl
import ru.voodoo420.data.repositories.CitiesRepositoryImpl
import ru.voodoo420.data.repositories.WeatherRepositoryImpl
import ru.voodoo420.domain.repositories.CitiesRepository
import ru.voodoo420.domain.repositories.WeatherRepository
import ru.voodoo420.domain.usecases.*
import ru.voodoo420.weatherapp.viewmodels.*

val repositoriesModule = module {
    single { RoomAppDatabase.buildDatabase(androidContext()) }
    single<FromApiToEntitiesConverter> { FromApiToEntitiesConverterImpl() }
    single<FromEntitiesToDbConverter> { FromEntitiesToDbConverterImpl() }
    single<WeatherProvider> { WeatherProviderImpl(get())}
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<CitiesRepository> { CitiesRepositoryImpl(get(),(androidContext()), get()) }
}

val useCasesModule = module {
    single { GetForecastUseCase(get()) }
    single { GetCurrentWeatherUseCase(get()) }
    single { GetCitiesWeatherUseCase(get(),get()) }
    single { GetObservableCoordFromDbUseCase(get()) }
    single { SetUtilValuesToDbUseCase(get(), get()) }
    single { AddCityUseCase(get(), get()) }
    single { DeleteCityUseCase(get()) }
}

val viewModelsModule = module {
    viewModel { ForecastViewModel(get(),get()) }
    viewModel { CurrentWeatherViewModel(get(), get(), get()) }
    viewModel { CitiesViewModel(get(), get(), get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { AddCityViewModel(get(), get()) }
}

val modules = listOf(repositoriesModule, useCasesModule, viewModelsModule)