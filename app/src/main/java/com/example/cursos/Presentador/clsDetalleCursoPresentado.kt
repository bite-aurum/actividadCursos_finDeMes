package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsDetalleCursoModelo
import com.example.cursos.Modelo.dataCursoDetalle

interface IDetalleCursoVista {
    fun mostrarDetalle(curso: dataCursoDetalle)
    fun mostrarError(mensaje: String)
}

class clsDetalleCursoPresentador(private val vista: IDetalleCursoVista) {

    private val modelo = clsDetalleCursoModelo()

    fun cargarDetalle(idCurso: Int) {
        modelo.obtenerDetalle(
            idCurso,
            onExito = { curso -> vista.mostrarDetalle(curso) },
            onError = { mensaje -> vista.mostrarError(mensaje) }
        )
    }
}