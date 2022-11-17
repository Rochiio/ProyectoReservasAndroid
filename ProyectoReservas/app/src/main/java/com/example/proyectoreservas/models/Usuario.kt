package com.example.proyectoreservas.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

/**
 * Clase de usuario
 */


@OptIn(ExperimentalSerializationApi::class)
@Entity(tableName = "Users", indices = [Index(value = ["email"], unique = true)])
@Serializable
@Serializer(Usuario::class)
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