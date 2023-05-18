package org.d3if3068.assesment001.ui.Converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3068.assesment001.db.ConverterDao
import org.d3if3068.assesment001.db.ConverterEntity

class ConverterViewModel(private val db: ConverterDao) : ViewModel() {
    val fahrenheitResult: MutableLiveData<Double> = MutableLiveData()
    var celciusInput: Double = 0.0
    val data = db.getLastConverter()

    fun convertTemperature(celcius: Double) {
        celciusInput = celcius
        val fahrenheit = (celcius * 9 / 5) + 32
        fahrenheitResult.value = fahrenheit

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(ConverterEntity(celcius = celcius, fahrenheit = fahrenheit))
            }
        }
    }
}
