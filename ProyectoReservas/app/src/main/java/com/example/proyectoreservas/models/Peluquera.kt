package com.example.proyectoreservas.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Peluqueras")
data class Peluquera (
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var nombre: String,
    val correo: String,
    val telefono: String,
){
}