package com.example.cursos.Modelo

import com.example.cursos.InterfaceApi.ApiService
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlin.lazy


object RetrofitCliente {
    private const val BASE_URL = "http://192.168.1.111/cursos_api/"
    private val jsonConfig = Json{
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }
    val apiService: ApiService by lazy {
        val contenType= "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConfig.asConverterFactory(contenType))
            .build()
            .create(ApiService::class.java)


    }
}