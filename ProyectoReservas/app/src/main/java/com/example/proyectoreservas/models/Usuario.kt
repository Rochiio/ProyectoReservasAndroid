package com.example.proyectoreservas.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Clase de usuario
 */
@Serializable
@Entity(tableName = "Users", indices = [Index(value = ["email"], unique = true)])
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    val name: String="",
    val surname: String?="",
    val telephone: String?="",
    val email: String="",
    val password: String="",
    val admin: Boolean= false
) {
}