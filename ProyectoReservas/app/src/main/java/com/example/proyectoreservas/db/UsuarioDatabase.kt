package com.example.proyectoreservas.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectoreservas.models.Usuario

@Database(entities = arrayOf(Usuario::class), version =1)
abstract class UsuarioDatabase: RoomDatabase(){
    abstract fun usuarioDao(): UsuarioDao
}