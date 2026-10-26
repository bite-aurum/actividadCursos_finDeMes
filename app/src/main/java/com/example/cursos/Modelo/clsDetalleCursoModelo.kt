package com.example.cursos.Modelo

class clsDetalleCursoModelo {
    suspend fun obtenerDetalle(id: Int): dataCursoDetalle {
        return RetrofitCliente.apiService.obtenerDetalleCurso(id)
    }
}