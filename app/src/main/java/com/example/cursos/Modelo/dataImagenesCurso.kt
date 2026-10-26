package com.example.cursos.Modelo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataImagenesCurso(
    @SerialName("id_curso") val id_curso: Int = 0,
    @SerialName("url_imagen") val url_imagen: String = ""
)
