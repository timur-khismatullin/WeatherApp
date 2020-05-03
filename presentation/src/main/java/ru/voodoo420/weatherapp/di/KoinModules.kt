package ru.voodoo420.weatherapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.voodoo420.data.converters.ForecastConverter
import ru.voodoo420.data.converters.ForecastConverterImpl
import ru.voodoo420.data.remote.providers.ForecastProvider
import ru.voodoo420.data.remote.providers.ForecastProviderImpl
import ru.voodoo420.data.repository.ForecastRepositoryImpl
import ru.voodoo420.domain.repository.ForecastRepository
import ru.voodoo420.domain.usecases.GetForecastByCoordUseCase
import ru.voodoo420.weatherapp.viewmodels.ForecastViewModel

val repositoryModule = module {
    single<ForecastProvider> {ForecastProviderImpl()}
    single<ForecastConverter> {ForecastConverterImpl()}
    single<ForecastRepository> {ForecastRepositoryImpl(get(), get())}
}

val useCasesModule = module {
    single { GetForecastByCoordUseCase(get()) }
}

val forecastModule = module {
    viewModel { ForecastViewModel(get()) }
}