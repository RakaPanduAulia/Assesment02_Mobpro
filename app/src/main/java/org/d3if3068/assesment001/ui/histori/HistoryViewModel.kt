package org.d3if3068.assesment001.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if3068.assesment001.db.ConverterDao

class HistoriViewModel(db: ConverterDao) : ViewModel() {
    val data = db.getLastConverter()
}