package ru.voodoo420.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast_larger_card.*
import ru.voodoo420.weatherapp.R
import ru.voodoo420.domain.entities.ForecastUnit

import kotlin.collections.ArrayList

class ForecastLargerCardsAdapter :
    RecyclerView.Adapter<ForecastLargerCardsAdapter.ViewHolder>() {

    private val forecastUnits: MutableList<ForecastUnit> = ArrayList()

    fun setData(forecastList: List<ForecastUnit>) {
        forecastUnits.clear()
        forecastUnits.addAll(forecastList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_larger_card, parent, false)
        )
    }

    override fun getItemCount(): Int = forecastUnits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastUnits[position])

    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(unit: ForecastUnit) = with(unit) {
            card_description.text = description
            card_date_time.text = dateTime
            Glide.with(containerView)
                .load("https://openweathermap.org/img/wn/$icon@2x.png")
                .into(card_icon)
            containerView.context.apply {
                card_temperature.text = getString(R.string.temp, temperature)
                card_feels_like.text = getString(R.string.feels, feelsLike)
                card_humidity.text = getString(R.string.humidity, humidity)
                card_wind.text = getString(R.string.wind, wind)
                card_min.text = getString(R.string.temp_min, min)
                card_max.text = getString(R.string.temp_max, max)
            }

        }
    }
}
