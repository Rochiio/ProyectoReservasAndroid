package com.example.proyectoreservas.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName="Citas")
data class Cita(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var clienteEmail: String,
    val fecha: String,//Parse LocalDate
    val hora: String,
    val peluquera: String,
) {
}