
package com.example.sorrisomarcado.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entidade responsável por armazenar os dados dos pacientes.
@Entity(tableName = "pacientes")
data class Paciente(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val dataNascimento: String = ""
)
