package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursoDetalle(
    @SerialName("id_curso") val idCurso: Int,
    val codigo: String,
    val nombre: String,
    val descripcion: String? = null,
    val imagen: String? = null,
    val salon: String,
    val edificio: String? = null,
    @SerialName("total_horas") val totalHoras: Int,
    @SerialName("dias_atencion") val diasAtencion: String,
    @SerialName("hora_inicio") val horaInicio: String,
    @SerialName("hora_fin") val horaFin: String
)

@Serializable
data class DetalleCursoResponse(
    val success: Boolean,
    val message: String? = null,
    val data: dataCursoDetalle? = null
)