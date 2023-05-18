package org.d3if3068.assesment001.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {
    val fahrenheitResult: MutableLiveData<Double> = MutableLiveData()
    var celciusInput: Double = 0.0

    fun convertTemperature(celcius: Double) {
        celciusInput = celcius
        val fahrenheit = (celcius * 9 / 5) + 32
        fahrenheitResult.value = fahrenheit
    }
}
