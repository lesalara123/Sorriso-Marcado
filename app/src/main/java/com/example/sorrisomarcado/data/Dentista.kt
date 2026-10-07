package com.example.sorrisomarcado.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dentistas")
data class Dentista(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val cro: String,
    val especialidade: String,
    val telefone: String
)
