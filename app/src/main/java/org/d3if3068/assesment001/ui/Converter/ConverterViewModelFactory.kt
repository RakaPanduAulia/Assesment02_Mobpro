package org.d3if3068.assesment001.ui.Converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3068.assesment001.db.ConverterDao

class ConverterViewModelFactory(private val db: ConverterDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConverterViewModel::class.java)) {
            return ConverterViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}