package com.example.cursos.Modelo

class clsDetalleCursoModelo {

    fun obtenerDetalle(
        id: Int,
        onExito: (dataCursoDetalle) -> Unit,
        onError: (String) -> Unit
    ) {
        val api = RetrofitCliente.instancia.create(ApiService::class.java)

        api.getDetalleCurso(id).enqueue(object : Callback<DetalleCursoResponse> {
            override fun onResponse(call: Call<DetalleCursoResponse>,
                response: Response<DetalleCursoResponse>
            ) {
                val cuerpo = response.body()
                val curso = cuerpo?.data
                if (response.isSuccessful && cuerpo?.success == true && curso != null) {
                    onExito(curso)
                } else {
                    onError(cuerpo?.message ?: "No se pudo cargar el curso")
                }
            }

            override fun onFailure(call: Call<DetalleCursoResponse>, t: Throwable) {
                onError("Error de conexión: ${t.message}")
            }
        })
    }
}