package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRespuesta(
    @SerialName("exito") val exito: Boolean,
    @SerialName("mensaje") val mensaje: String
)
