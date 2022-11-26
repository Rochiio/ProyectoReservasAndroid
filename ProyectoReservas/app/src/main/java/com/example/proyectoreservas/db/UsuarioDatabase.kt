package com.example.proyectoreservas.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Usuario

@Database(entities = arrayOf(Usuario::class, Cita::class), version =2)
abstract class UsuarioDatabase: RoomDatabase(){
    abstract fun usuarioDao(): UsuarioDao
    abstract fun citasDao(): CitaDao
}