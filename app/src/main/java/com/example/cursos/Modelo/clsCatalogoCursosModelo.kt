package com.example.cursos.Modelo

class clsCatalogoCursosModelo {
    // Obtiene los cursos
    suspend fun obtenerCursos(carreraid: Int): List<dataCursos> {
        return RetrofitCliente.apiService.obtenerCursos(carreraid)
    }
}