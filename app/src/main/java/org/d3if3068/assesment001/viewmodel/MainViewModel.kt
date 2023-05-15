package org.d3if3068.assesment001.viewmodel

import androidx.lifecycle.ViewModel
import org.d3if3068.assesment001.model.TemperatureConverter

class MainViewModel : ViewModel() {
    private val temperatureConverter = TemperatureConverter()

    fun convertTemperature(celsius: Double): Double {
        return temperatureConverter.convertCelsiusToFahrenheit(celsius)
    }
}
