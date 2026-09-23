package com.example.sorrisomarcado.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConsultaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(consulta: Consulta)

    @Query("SELECT * FROM consultas")
    fun getAll(): Flow<List<Consulta>>

    @Delete
    suspend fun delete(consulta: Consulta)
}
