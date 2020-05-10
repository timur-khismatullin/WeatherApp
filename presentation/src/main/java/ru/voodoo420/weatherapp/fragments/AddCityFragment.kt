package ru.voodoo420.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_add_city.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.voodoo420.weatherapp.R
import ru.voodoo420.weatherapp.viewmodels.AddCityViewModel

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

        lifecycleScope.launch {
            addCityViewModel.clearLocation()
        }

        add_city_add.setOnClickListener {
            lifecycleScope.launch {
                addCityViewModel.addCityToDb(add_city_editText.text.toString())
//                addCityViewModel.setCoordByName(add_city_editText.text.toString())
//                add_city_weather_card.visibility = View.VISIBLE
            }
        }

        addCityViewModel.viewState.observe(viewLifecycleOwner, Observer {
            add_city_editText.setText(it.name)
        })
    }
}