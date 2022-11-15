package com.example.proyectoreservas.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoreservas.models.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM Users")
    suspend fun getAllUsers():List<Usuario>

    @Insert
    suspend fun addUser(user: Usuario)

    @Update
    suspend fun updateUser(user: Usuario)

    @Delete
    suspend fun deleteUser(user: Usuario)

//    @Query("SELECT * FROM Users WHERE email= :email")
//    suspend fun getUserByEmail(email: String): Usuario?
}