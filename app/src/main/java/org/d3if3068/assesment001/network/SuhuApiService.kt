package org.d3if3068.assesment001.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3068.assesment001.Model.Termometer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/RakaPanduAulia/Assesment02_Mobpro/JSON/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SuhuApiService {
    @GET("OtherConv")
    suspend fun getConv(): List<Termometer>
}
object SuhuApi {
    val service: SuhuApiService by lazy {
        retrofit.create(SuhuApiService::class.java)
    }
}

fun getConvUrl(imageId: String): String {
    return SuhuApi.service.getConv()
}
