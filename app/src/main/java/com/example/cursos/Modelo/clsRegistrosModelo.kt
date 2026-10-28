package com.example.cursos.Modelo

class clsRegistrosModelo {

    suspend fun obtenerRegistrosPorCarrera(carreraId: Int): List<dataCursos> {
        return RetrofitCliente.apiService.obtenerInscripciones(carreraId)
    }
}