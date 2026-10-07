package com.example.sorrisomarcado.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "procedimentos")
data class Procedimento(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val descricao: String,
    val valor: Double
)
