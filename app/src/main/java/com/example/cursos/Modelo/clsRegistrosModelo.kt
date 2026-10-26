package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class clsRegistrosModelo(
    @SerialName("id_curso") val id: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("imagen") val imagen: String = "",
    @SerialName("inscritos") val inscritos: Int = 0,
    @SerialName("cupo_total") val cupoTotal: Int = 0,
    @SerialName("id_carrera") val idCarrera: Int = 0,
    @SerialName("carrera") val carrera: String = ""
)

@Serializable
data class clsRespuestaRegistros(
    @SerialName("total") val total: Int = 0,
    @SerialName("cursos") val cursos: List<clsRegistrosModelo> = emptyList()
)