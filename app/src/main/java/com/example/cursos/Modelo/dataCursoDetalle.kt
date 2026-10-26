package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursoDetalle(
    @SerialName("id_curso") val idCurso: Int = 0,
    @SerialName("codigo") val codigo: String = "",
    @SerialName("nombre") val nombre: String = "",
    @SerialName("descripcion") val descripcion: String = "",
    @SerialName("imagen") val imagen: String = "",
    @SerialName("salon") val salon: String = "",
    @SerialName("edificio") val edificio: String = "",
    @SerialName("total_horas") val totalHoras: Int = 0,
    @SerialName("dias_atencion") val diasAtencion: String = "",
    @SerialName("hora_inicio") val horaInicio: String = "",
    @SerialName("hora_fin") val horaFin: String = ""
)