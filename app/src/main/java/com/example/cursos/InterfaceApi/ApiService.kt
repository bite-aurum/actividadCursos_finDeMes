package com.example.cursos.InterfaceApi

import com.example.cursos.Modelo.dataCursos
import retrofit2.http.GET

interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCursos>
}