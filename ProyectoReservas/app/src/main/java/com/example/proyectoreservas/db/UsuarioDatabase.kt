package com.example.proyectoreservas.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Peluquera
import com.example.proyectoreservas.models.Usuario

/**
 * Tablas de la base de datos
 */
@Database(entities = arrayOf(Usuario::class, Cita::class, Peluquera::class), version =3)
abstract class UsuarioDatabase: RoomDatabase(){
    abstract fun usuarioDao(): UsuarioDao
    abstract fun citasDao(): CitaDao
    abstract fun peluquerasDao(): PeluqueraDAO
}