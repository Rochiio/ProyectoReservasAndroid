package com.example.proyectoreservas.db

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration

class UsuarioApplication: Application() {

    companion object{
        lateinit var database: UsuarioDatabase
    }


    override fun onCreate() {
        super.onCreate()

        //Constructor de la base de datos
        database = Room.databaseBuilder(this, UsuarioDatabase::class.java,
            "UsuarioDatabase").fallbackToDestructiveMigration().build()
    }
}