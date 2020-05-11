package ru.voodoo420.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_forecast_larger_cards.*
import ru.voodoo420.weatherapp.R
import ru.voodoo420.weatherapp.viewmodels.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.voodoo420.weatherapp.adapters.ForecastLargerCardsAdapter
import ru.voodoo420.weatherapp.utils.NetworkUtil

class ForecastLargerCardsFragment : Fragment() {

    private val fragmentArgs: ForecastLargerCardsFragmentArgs by navArgs()
    private val forecastViewModel: ForecastViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast_larger_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterRecycler()
    }

    private fun initAdapterRecycler() {
        NetworkUtil.getNetworkLiveData(requireContext())
            .observe(viewLifecycleOwner, Observer { connected ->
                if (connected) {
                    val adapter = ForecastLargerCardsAdapter()
                    forecastViewModel.viewState.observe(viewLifecycleOwner, Observer {
                        adapter.setData(it)

                    })
                    larger_cards_recycler.layoutManager = LinearLayoutManager(this.context)
                    larger_cards_recycler.adapter = adapter
                    larger_cards_recycler.postDelayed({
                        larger_cards_recycler.smoothScrollToPosition(
                            fragmentArgs.pos
                        )
                    }, 500)
                }
            })
    }
}