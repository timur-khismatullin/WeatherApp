package ru.voodoo420.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_add_city.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.voodoo420.domain.entities.CityCurrentWeather
import ru.voodoo420.weatherapp.NavGraphDirections
import ru.voodoo420.weatherapp.R
import ru.voodoo420.weatherapp.utils.NetworkUtil
import ru.voodoo420.weatherapp.viewmodels.AddCityViewModel
import java.util.*

class AddCityFragment : Fragment() {

    private val addCityViewModel: AddCityViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCurrentLocationObserver()
        addButtonListeners()
        addTextChangedListener()
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun addCurrentLocationObserver() {
        lifecycleScope.launch {
            addCityViewModel.clearLocation()
            addCityViewModel.observableCoordFromDb.observe(viewLifecycleOwner, Observer {
                add_city_editText.setText(it.name)
            })
        }
    }

    private fun addButtonListeners() {
        add_city_add_button.setOnClickListener {
            lifecycleScope.launch {
                addCityViewModel.addCityToDb(add_city_editText.text.toString())
                findNavController().navigate(NavGraphDirections.toCities())
            }
        }
    }

    private fun addTextChangedListener() {
        NetworkUtil.getNetworkLiveData(requireContext())
            .observe(viewLifecycleOwner, Observer { connected ->
                if (connected) {
                    add_city_editText.addTextChangedListener {
                        var timer = Timer()
                        val delay: Long = 300
                        timer.cancel()
                        timer = Timer()
                        timer.schedule(
                            object : TimerTask() {
                                override fun run() {
                                    lifecycleScope.launch {
                                        if (connected) { //todo
                                            val result =
                                                addCityViewModel.getWeather(add_city_editText.text.toString())
                                            if (result.code == 200) {
                                                initCard(result.cityCurrentWeather!!)
                                            }
                                        }
                                    }
                                }
                            }, delay
                        )
                    }
                }
            })
    }

    private fun initCard(currentWeather: CityCurrentWeather) = with(currentWeather) {
        add_city_name.text = getString(R.string.city_name, city.name, city.country)
        weather.apply {
            add_city_description.text = description
            add_city_temperature.text = getString(R.string.temp, temperature)
            add_city_feels_like.text = getString(R.string.feels, feels)
            add_city_wind.text = getString(R.string.wind, wind)
            add_city_humidity.text = getString(R.string.humidity, humidity)
            add_city_min.text = getString(R.string.temp_min, minTemp)
            add_city_max.text = getString(R.string.temp_max, maxTemp)
        }
        Glide.with(requireContext())
            .load("https://openweathermap.org/img/wn/${weather.icon}@2x.png")
            .into(add_city_icon)

        add_city_weather_card.visibility = View.VISIBLE
    }
}