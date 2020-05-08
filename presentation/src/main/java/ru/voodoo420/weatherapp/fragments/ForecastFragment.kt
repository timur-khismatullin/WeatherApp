package ru.voodoo420.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.voodoo420.weatherapp.R
import ru.voodoo420.weatherapp.adapters.ForecastAdapter
import ru.voodoo420.weatherapp.viewmodels.ForecastViewModel

class ForecastFragment : Fragment() {

    private val forecastViewModel: ForecastViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterRecycler()
    }

    private fun initAdapterRecycler() {
        val adapter = ForecastAdapter()
        forecastViewModel.viewState.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        forecast_recycler.layoutManager = LinearLayoutManager(this.context)
        forecast_recycler.adapter = adapter
    }
}