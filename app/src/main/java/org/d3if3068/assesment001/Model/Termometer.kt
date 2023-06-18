package org.d3if3068.assesment001.Model

import com.squareup.moshi.Json

data class Termometer(
//    val judul: String,
//    val rumus: String,
//    val gambar: String
    @Json(name = "judul") val judul: String,
    @Json(name = "rumus") val rumus: String,
    @Json(name = "gambar") val gambar: String)
