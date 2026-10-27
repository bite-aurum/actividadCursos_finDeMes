package com.example.cursos.Modelo

class clsDetalleCursoModelo {
    suspend fun obtenerDetalleCurso(id: Int): dataCursoDetalle {
        return RetrofitCliente.apiService.obtenerDetalleCurso(id)
    }
}