package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsDetalleCursoModelo
import com.example.cursos.Modelo.dataCursoDetalle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface IDetalleCursoVista {
    fun mostrarDetalle(curso: dataCursoDetalle)
    fun mostrarError(mensaje: String)
}

class clsDetalleCursoPresentador(private val vista: IDetalleCursoVista) {

    private val modelo = clsDetalleCursoModelo()

    fun cargarDetalle(idCurso: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val curso = modelo.obtenerDetalle(idCurso)
                vista.mostrarDetalle(curso)
            } catch (e: Exception) {
                vista.mostrarError("No se pudo cargar el curso: ${e.message}")
            }
        }
    }
}