package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsCarrerasModelo
import com.example.cursos.Modelo.clsRegistrosModelo
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Vista.clsVerRegistros
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsRegistrosPresentador(val vista: clsVerRegistros) {

    private val modeloCarreras = clsCarrerasModelo()
    private val modeloRegistros = clsRegistrosModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)
                cargarRegistros(0)
            } catch (e: Exception) {
                vista.mostrarError("Error: ${e.localizedMessage}")
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {
        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]
            cargarRegistros(carrera.id)
        }
    }

    private fun cargarRegistros(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val registros = modeloRegistros.obtenerRegistrosPorCarrera(carreraId)
                vista.mostrarRegistros(registros)
                val totalInscritos = registros.sumOf { it.inscritos }
                vista.mostrarTotal(totalInscritos)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar registros: ${e.localizedMessage}")
            }
        }
    }
}
