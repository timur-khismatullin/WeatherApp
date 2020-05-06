package ru.voodoo420.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.*
import ru.voodoo420.weatherapp.R
import ru.voodoo420.domain.entities.City
import ru.voodoo420.domain.entities.CurrentWeather

import kotlin.collections.ArrayList

class CitiesAdapter(private val functionForListener: (Pair<Float, Float>) -> Unit) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    private val cities: MutableList<Pair<City, CurrentWeather>> = ArrayList()

    fun setData(citiesList: List<Pair<City, CurrentWeather>>){
        cities.clear()
        cities.addAll(citiesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cities[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { functionForListener(Pair(item.first.lat, item.first.lon)) }
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(item: Pair<City, CurrentWeather>) = with(item) {
            Glide.with(containerView)
                .load("https://openweathermap.org/img/wn/${second.icon}@2x.png")
                .into(cities_icon)
            cities_name.text = first.name
            cities_temperature.text = second.temperature.toString()
        }
    }
}
