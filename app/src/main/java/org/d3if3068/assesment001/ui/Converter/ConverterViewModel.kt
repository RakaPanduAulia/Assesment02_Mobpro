package org.d3if3068.assesment001.ui.Converter

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3068.assesment001.Model.HasilConverter
import org.d3if3068.assesment001.Model.hitungConverter
import org.d3if3068.assesment001.db.ConverterDao
import org.d3if3068.assesment001.db.ConverterEntity
import org.d3if3068.assesment001.network.ApiStatus
import org.d3if3068.assesment001.network.SuhuApi
import org.d3if3068.assesment001.network.UpdateWorker
import java.util.concurrent.TimeUnit

class ConverterViewModel(private val db: ConverterDao) : ViewModel() {
    private val hitungConverter = MutableLiveData<HasilConverter>()
    private val data = MutableLiveData<String>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
//                val result = SuhuApi.service.getConv()
//                Log.d("ConverterViewModel", "Success: $result")
//                val response = SuhuApi.service.getConv()
                data.postValue(SuhuApi.service.getConv())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("ConverterViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    val fahrenheitResult: MutableLiveData<Double> = MutableLiveData()
    var celciusInput: Double = 0.0

    fun convertTemperature(celcius: Double): Double {
//        celciusInput = celcius
        val fahrenheit = (celcius * 9 / 5) + 32
        fahrenheitResult.value = fahrenheit

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
        return fahrenheit

    }
    fun get(get: Float): Float {
        val to = get
        return to
    }

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
