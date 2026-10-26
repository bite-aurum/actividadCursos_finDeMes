package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroRequest(
    @SerialName("curso_id") val cursoId: Int,
    @SerialName("matricula") val matricula: String,
    @SerialName("correo") val correo: String,
    @SerialName("fecha") val fecha: String)


