package ru.voodoo420.data.remote.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.voodoo420.data.models.FiveDaysForecast

interface OpenWeatherMapApiService {

    @GET("forecast")
    suspend fun loadForecast(
        @Query("lat") lat: Float,
        @Query("lon") lng: Float,
        @Query("appid") keyApi: String,
        @Query("units") units: String
    ): FiveDaysForecast


    companion object Factory{
        private const val baseUrl = "https://api.openweathermap.org/data/2.5/"
        private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        fun create(): OpenWeatherMapApiService{
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherMapApiService::class.java)
        }
    }
}