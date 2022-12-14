package com.example.proyectoreservas.db

import androidx.room.*
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Peluquera

@Dao
interface PeluqueraDAO {
    @Query("SELECT * FROM Peluqueras")
    suspend fun getAllPeluqueras():List<Peluquera>

    @Query("SELECT * FROM Peluqueras WHERE correo = :email")
    suspend fun findByEmail(email:String):Peluquera?

    @Insert
    suspend fun addPeluquera(peluquera: Peluquera)

    @Delete
    suspend fun deleteCita(cita: Cita)
}