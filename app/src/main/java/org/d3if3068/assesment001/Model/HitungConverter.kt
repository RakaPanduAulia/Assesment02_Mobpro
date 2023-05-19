package org.d3if3068.assesment001.Model

import org.d3if3068.assesment001.db.ConverterEntity

fun ConverterEntity.hitungConverter(): HasilConverter {
    val fahrenheit = (celcius * 9 / 5) + 32
    return HasilConverter(fahrenheit)
}