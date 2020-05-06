package ru.voodoo420.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import ru.voodoo420.weatherapp.R
import ru.voodoo420.domain.entities.ForecastUnit
import kotlinx.android.synthetic.main.item_forecast_unit.*

import kotlin.collections.ArrayList

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private val forecastUnits: MutableList<ForecastUnit> = ArrayList()

    fun setData(forecastList: List<ForecastUnit>){
        forecastUnits.clear()
        forecastUnits.addAll(forecastList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_unit, parent, false))
    }

    override fun getItemCount(): Int = forecastUnits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(forecastUnits[position])

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(unit: ForecastUnit) = with(unit) {
            Glide.with(containerView)
                .load("https://openweathermap.org/img/wn/${icon}@2x.png")
                .into(fc_unit_icon)
            fc_unit_date.text = date
            fc_unit_temperature.text = temperature.toString()
        }
    }
}
