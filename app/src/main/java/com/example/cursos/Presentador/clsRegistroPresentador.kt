package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsInscripcionesModelo
import com.example.cursos.Vista.RegistroCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsRegistroPresentador(val vista: RegistroCursoActivity) {

    private val modelo = clsInscripcionesModelo()

    fun confirmarInscripcion(cursoId: Int, matricula: String, fecha: String, correo: String) {
        if (cursoId <= 0 || matricula == "" || fecha == "" || correo == "") {
            vista.mostrarError("Por favor, completa todos los campos solicitados")
            return
        }

        if (!correo.contains("@")) {
            vista.mostrarError("El correo no es válido")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.registrarInscripcion(cursoId, matricula, correo, fecha)

                if (respuesta.exito) {
                    vista.inscripcionExitosa(respuesta.mensaje)
                } else {
                    vista.mostrarError(respuesta.mensaje)
                }
            } catch (e: Exception) {
                vista.mostrarError("Error de conexión: " + e.localizedMessage)
            }
        }
    }
}
