package com.example.cursos.Presentador

import com.example.cursos.MainActivity
import com.example.cursos.Modelo.clsCarrerasModelo
import com.example.cursos.Modelo.clsCatalogoCursosModelo
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCursos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class clsCatalogoCursosPresentador(val vista: MainActivity)
{
    private val modeloCarreras = clsCarrerasModelo()
    private val modeloCursos = clsCatalogoCursosModelo()
    private var listaCarreras : List<dataCarreras> = emptyList()
    fun iniciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)
                cargarCursos(0)
            }
            catch (e: Exception){
                vista.mostrarError("Error:${e.localizedMessage}")
            }
        }
    }
    fun seleccionarCarrera(posicion: Int){
        if (posicion >= 0 && posicion < listaCarreras.size){
            val carreras = listaCarreras[posicion]
            cargarCursos(carreras.id)
        }
    }
    private fun cargarCursos(carreraid: Int){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val cursos = modeloCursos.obtenerCursos(carreraid)
                vista.mostrarCursos(cursos)
            } catch (e: Exception){
                vista.mostrarError("Error al cargar productos:${e.localizedMessage}")
            }
        }
    }
    fun seleccionarCurso(curso: dataCursos){
        vista.mostrarDetalleCurso(curso)
    }

}