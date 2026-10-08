package com.example.sorrisomarcado.data

import kotlinx.coroutines.flow.Flow

class ConsultaRepository(private val consultaDao: ConsultaDao) {
    fun getAllConsultas(): Flow<List<Consulta>> = consultaDao.getAll()
}
