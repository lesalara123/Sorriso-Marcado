package com.example.sorrisomarcado.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disponibilidades")
data class Disponibilidade(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val dentistaId: Int,
    val data: String,
    val horaInicio: String,
    val horaFim: String,
    val disponivel: Boolean = true
)