
package com.example.sorrisomarcado.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DisponibilidadeDao {

    @Insert
    suspend fun inserir(disponibilidade: Disponibilidade)

    @Update
    suspend fun atualizar(disponibilidade: Disponibilidade)

    @Delete
    suspend fun excluir(disponibilidade: Disponibilidade)

    @Query("SELECT * FROM disponibilidades ORDER BY data, horaInicio")
    suspend fun listarTodas(): List<Disponibilidade>

    @Query("SELECT * FROM disponibilidades WHERE dentistaId = :dentistaId ORDER BY data, horaInicio")
    suspend fun listarPorDentista(dentistaId: Int): List<Disponibilidade>

    @Query("SELECT * FROM disponibilidades WHERE data = :data AND disponivel = 1 ORDER BY horaInicio")
    suspend fun listarDisponiveisPorData(data: String): List<Disponibilidade>

    @Query("UPDATE disponibilidades SET disponivel = :disponivel WHERE id = :id")
    suspend fun alterarSituacao(id: Int, disponivel: Boolean)
}
