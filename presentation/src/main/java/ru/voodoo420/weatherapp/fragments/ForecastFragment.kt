package ru.voodoo420.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_forecast.*
import ru.voodoo420.weatherapp.R
import ru.voodoo420.weatherapp.adapters.ForecastAdapter
import ru.voodoo420.weatherapp.entities.ForecastUnit

class ForecastFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecast_recycler.layoutManager = LinearLayoutManager(this.context)
        val adapter = ForecastAdapter()
        adapter.setData(listOf(
            ForecastUnit("today", 12.0, "123"),
            ForecastUnit("tommorow", 15.0, "123")
        ))
        forecast_recycler.adapter = adapter
    }
}