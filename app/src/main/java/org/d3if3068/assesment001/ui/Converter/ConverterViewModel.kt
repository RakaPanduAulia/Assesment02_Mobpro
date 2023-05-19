package org.d3if3068.assesment001.ui.Converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3068.assesment001.Model.HasilConverter
import org.d3if3068.assesment001.Model.hitungConverter
import org.d3if3068.assesment001.db.ConverterDao
import org.d3if3068.assesment001.db.ConverterEntity

class ConverterViewModel(private val db: ConverterDao) : ViewModel() {
    private val hitungConverter = MutableLiveData<HasilConverter>()

    val fahrenheitResult: MutableLiveData<Double> = MutableLiveData()
    var celciusInput: Double = 0.0

    fun convertTemperature(celcius: Double) {
//        celciusInput = celcius
//        val fahrenheit = (celcius * 9 / 5) + 32
//        fahrenheitResult.value = fahrenheit

        val dataConverter = ConverterEntity(
            celcius = celcius,
            fahrenheit = (celcius * 9 / 5) + 32
        )
        hitungConverter.value = dataConverter.hitungConverter()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataConverter)
            }
        }
    }
}
