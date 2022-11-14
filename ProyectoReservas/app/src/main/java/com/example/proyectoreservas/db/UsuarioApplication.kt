package com.example.proyectoreservas.db

import android.app.Application
import androidx.room.Room

class UsuarioApplication: Application() {
    companion object{
        lateinit var database: UsuarioDatabase
    }
    //sobre escribimos
    override fun onCreate() {
        super.onCreate()

        //construimos la database
        database = Room.databaseBuilder(this,
            UsuarioDatabase::class.java,
            "UsuarioDatabase")
            .build()
    }
}