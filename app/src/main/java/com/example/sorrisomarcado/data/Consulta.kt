package com.example.sorrisomarcado.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consultas")
data class Consulta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val data: String,
    val horario: String,
    val dentista: String,
    val procedimento: String
)
