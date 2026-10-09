
package com.example.sorrisomarcado.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PacienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(paciente: Paciente)

    @Update
    suspend fun update(paciente: Paciente)

    @Delete
    suspend fun delete(paciente: Paciente)

    @Query("SELECT * FROM pacientes ORDER BY nome COLLATE NOCASE ASC")
    fun getAll(): Flow<List<Paciente>>

    @Query("SELECT * FROM pacientes WHERE id = :id")
    suspend fun getById(id: Int): Paciente?

    @Query("SELECT * FROM pacientes WHERE cpf = :cpf LIMIT 1")
    suspend fun getByCpf(cpf: String): Paciente?
}
