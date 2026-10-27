package com.example.cursos.InterfaceApi

import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCursos
import com.example.cursos.Modelo.dataVerResgistros
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCarreras>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(
        @Query("id_carrera") idCarrera: Int = 0
    ): List<dataCursos>

    @GET("obtener_inscripciones.php")
    fun obtenerInscripciones(@Query("id_carrera") idCarrera: Int= 0
    ): List<dataVerResgistros>

}