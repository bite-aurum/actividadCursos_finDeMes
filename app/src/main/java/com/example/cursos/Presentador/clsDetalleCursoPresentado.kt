package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsDetalleCursoModelo
import com.example.cursos.Modelo.dataCursoDetalle
import com.example.cursos.Vista.DetalleCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleCursoPresentador(private val vista: DetalleCursoActivity) {
    private val modelo = clsDetalleCursoModelo()

    fun cargarDetalle(cursoId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Aquí se consume obtenerDetalleCurso del modelo
                val curso: dataCursoDetalle = modelo.obtenerDetalleCurso(cursoId)
                vista.mostrarDetalle(curso)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar detalle: " + e.localizedMessage)
            }
        }
    }
}