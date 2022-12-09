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
    var adminInicial = Usuario(1,"Carla","Admin","469181","admin","1234",true)
    var peluqueraInicial = Peluquera(0, "Donna", "donna@pelus.gmail.com", "963411")
    var peluqueraInicial2 = Peluquera(1, "Xavier", "xavi@pelus.gmail.com", "963411")
}