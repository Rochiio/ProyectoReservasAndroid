package com.example.proyectoreservas.models

import com.example.proyectoreservas.adapter.CitaAdapter
import com.example.proyectoreservas.adapter.PeluqueraAdapter
import java.util.*

/**
 * Datos necesarios para distintas actividades y fragmentos.
 */
object Data {
    private val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)


    var usuarioActual: Usuario? = null
    var citaAdapter: CitaAdapter? = null
    var peluqueraAdapter: PeluqueraAdapter? = null


    var userInicial = Usuario(0,"Pepe","Algo","6666","pepe","1234",false)
    var adminInicial = Usuario(0,"Clara","Algo","6666","clara","1234",true)
    var peluqueraInicial = Peluquera(0,"Carla", "carla@gmial.com", "666718151")
}