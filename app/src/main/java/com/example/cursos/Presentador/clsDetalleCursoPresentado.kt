package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsDetalleCursoModelo
import com.example.cursos.Vista.DetalleCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleCursoPresentador(private val activity: DetalleCursoActivity) {
    private val modelo = clsDetalleCursoModelo()
    fun cargarDetalle(idCurso: Int) { CoroutineScope(Dispatchers.Main).launch {
            try {
                val curso = modelo.obtenerDetalle(idCurso)
                activity.mostrarDetalle(curso)
            } catch (e: Exception) {
                activity.mostrarError("No se pudo cargar el curso: ${e.message}")
            }
        }
    }
}