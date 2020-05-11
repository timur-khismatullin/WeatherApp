package ru.voodoo420.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.voodoo420.domain.entities.City
import ru.voodoo420.weatherapp.NavGraphDirections
import ru.voodoo420.weatherapp.R
import ru.voodoo420.weatherapp.adapters.CitiesAdapter
import ru.voodoo420.weatherapp.utils.NetworkUtil
import ru.voodoo420.weatherapp.viewmodels.CitiesViewModel

class CitiesFragment : Fragment() {

    private val citiesViewModel: CitiesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterRecycler()
        cities_add.setOnClickListener { findNavController().navigate(NavGraphDirections.toAddCity()) }
    }

    private fun initAdapterRecycler() {
        NetworkUtil.getNetworkLiveData(requireContext())
            .observe(viewLifecycleOwner, Observer { connected ->
                if (connected) {
                    val adapter = CitiesAdapter({ coord ->
                        lifecycleScope.launch {
                            citiesViewModel.setCoordinates(coord) //todo if no internet -> java.net.SocketTimeoutException: timeout
                        }
                    }, { initDialog(it) })

                    citiesViewModel.viewState.observe(viewLifecycleOwner, Observer {
                        adapter.setData(it)
                    })

                    cities_recycler.layoutManager = LinearLayoutManager(this.context)
                    cities_recycler.adapter = adapter
                }
            })
    }

    private fun initDialog(city: City) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.city_deleting, city.name))
            setPositiveButton(android.R.string.yes) { _, _ ->
                lifecycleScope.launch {
                    citiesViewModel.deleteCity(city)
                }
            }
            setNegativeButton(android.R.string.no) { _, _ -> }
            show()
        }
    }
}