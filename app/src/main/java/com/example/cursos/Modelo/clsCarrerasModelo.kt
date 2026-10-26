package com.example.cursos.Modelo

class clsCarrerasModelo {
    suspend fun obtenerCarreras(): List<dataCarreras>{
        val datos= RetrofitCliente.apiService.obtenerCarrera()//pedidodedb

        val listaFinal=mutableListOf(dataCarreras(0,"Todas las carreras"))
        listaFinal.addAll(datos)

        return listaFinal//Retorna la lista directamente
    }
}