package org.d3if3068.assesment001.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "converter")
data class ConverterEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var celcius: Double,
    var fahrenheit: Double
)
