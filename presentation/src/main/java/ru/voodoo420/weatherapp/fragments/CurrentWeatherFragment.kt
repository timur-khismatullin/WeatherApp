package ru.voodoo420.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_current_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.voodoo420.weatherapp.R
import ru.voodoo420.weatherapp.viewmodels.CurrentWeatherViewModel

class CurrentWeatherFragment : Fragment() {

    private val currentWeatherModel: CurrentWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        currentWeatherModel.viewState.observe(viewLifecycleOwner, Observer {
            with(it) {
                current_city.text = city
                current_temperature.text = getString(R.string.temp, temperature)
                current_humidity.text = getString(R.string.humidity, humidity)
                current_wind.text = getString(R.string.wind, wind)
                current_description.text = description
                feels_like.text = getString(R.string.feels, feels)

                Glide.with(this@CurrentWeatherFragment)
                    .load("https://openweathermap.org/img/wn/${icon}@2x.png")
                    .into(current_icon)
            }
        })
    }
}