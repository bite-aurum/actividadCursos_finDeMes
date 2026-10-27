package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataVerResgistros(
    @SerialName("nombre") val nombre: String="",
    @SerialName("cupo total") val cupoTotal: Int=0,
)
