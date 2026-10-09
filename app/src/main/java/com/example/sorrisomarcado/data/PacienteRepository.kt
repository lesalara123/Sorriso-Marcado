
package com.example.sorrisomarcado.data




import com.example.sorrisomarcado.data.Paciente
import kotlinx.coroutines.flow.Flow


class PacienteRepository(
    private val pacienteDao: PacienteDao
) {

    fun getAllPacientes(): Flow<List<Paciente>> {
        return pacienteDao.getAll()
    }

    suspend fun getPacienteById(id: Int): Paciente? {
        return pacienteDao.getById(id)
    }

    suspend fun getPacienteByCpf(cpf: String): Paciente? {
        return pacienteDao.getByCpf(cpf)
    }

    suspend fun insertPaciente(paciente: Paciente) {
        pacienteDao.insert(paciente)
    }

    suspend fun updatePaciente(paciente: Paciente) {
        pacienteDao.update(paciente)
    }

    suspend fun deletePaciente(paciente: Paciente) {
        pacienteDao.delete(paciente)
    }
}
