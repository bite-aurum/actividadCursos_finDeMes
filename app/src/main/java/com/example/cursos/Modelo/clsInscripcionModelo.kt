package com.example.cursos.Modelo

class clsInscripcionesModelo {

    suspend fun registrarInscripcion(
        cursoId: Int,
        matricula: String,
        correo: String,
        fecha: String
    ): dataRespuesta {
        val request = dataRegistroRequest(cursoId, matricula, correo, fecha)
        return RetrofitCliente.apiService.registrarInscripcion(request)
    }
}