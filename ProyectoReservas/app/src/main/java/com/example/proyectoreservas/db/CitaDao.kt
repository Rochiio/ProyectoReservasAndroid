package com.example.proyectoreservas.db

import androidx.room.*
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Usuario

@Dao
interface CitaDao {
    @Query("SELECT * FROM Citas WHERE clienteEmail= :email")
    suspend fun getCitaByClienteEmail(email: String):List<Cita>

    @Query("SELECT * FROM Citas")
    suspend fun getAllCitas():List<Cita>

    @Query("SELECT * FROM Citas WHERE hora= :hora & fecha= :dia & peluquera= :peluquera")
    suspend fun peluqueraHoraDiaTieneCita(hora:String, dia:String, peluquera:String):Cita?

    @Insert
    suspend fun addCita(cita: Cita)

    @Update
    suspend fun updateCita(cita: Cita)

    @Delete
    suspend fun deleteCita(cita: Cita)

}