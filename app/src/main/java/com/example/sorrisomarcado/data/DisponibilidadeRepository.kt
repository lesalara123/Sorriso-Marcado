
package com.example.sorrisomarcado.data

class DisponibilidadeRepository(
    private val disponibilidadeDao: DisponibilidadeDao
) {

    suspend fun inserir(disponibilidade: Disponibilidade) {
        disponibilidadeDao.inserir(disponibilidade)
    }

    suspend fun atualizar(disponibilidade: Disponibilidade) {
        disponibilidadeDao.atualizar(disponibilidade)
    }

    suspend fun excluir(disponibilidade: Disponibilidade) {
        disponibilidadeDao.excluir(disponibilidade)
    }

    suspend fun listarTodas(): List<Disponibilidade> {
        return disponibilidadeDao.listarTodas()
    }

    suspend fun listarPorDentista(
        dentistaId: Int
    ): List<Disponibilidade> {
        return disponibilidadeDao.listarPorDentista(dentistaId)
    }

    suspend fun listarDisponiveisPorData(
        data: String
    ): List<Disponibilidade> {
        return disponibilidadeDao.listarDisponiveisPorData(data)
    }

    suspend fun alterarSituacao(
        id: Int,
        disponivel: Boolean
    ) {
        disponibilidadeDao.alterarSituacao(id, disponivel)
    }
}
