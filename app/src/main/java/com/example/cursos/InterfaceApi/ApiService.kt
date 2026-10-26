package com.example.cursos.InterfaceApi

import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCursos
import com.example.cursos.Modelo.dataRegistroRequest
import com.example.cursos.Modelo.dataRespuesta
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCarreras>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(
        @Query("id_carrera") idCarrera: Int = 0
    ): List<dataCursos>
    @POST("registrar_inscripcion.php")
    suspend fun registrarInscripcion(
        @Body request: dataRegistroRequest
    ): dataRespuesta
}