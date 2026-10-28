package com.example.cursos.Modelo

import com.example.cursos.InterfaceApi.ApiService
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlin.lazy


object RetrofitCliente {
    private const val BASE_URL = "http://10.75.1.98:8080/cursos_api/"
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
fun obtenerUrlImagen(imagen: String?): String {
    if (imagen.isNullOrEmpty()) return ""
    if (imagen.startsWith("http://") || imagen.startsWith("https://")) return imagen
    val ruta = if (imagen.startsWith("img/")) imagen else "img/$imagen"
    return "http://10.75.1.98:8080/cursos_api/$ruta"
}