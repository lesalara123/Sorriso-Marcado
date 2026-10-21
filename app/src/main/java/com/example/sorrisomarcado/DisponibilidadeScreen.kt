
package com.example.sorrisomarcado

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sorrisomarcado.data.Disponibilidade
import com.example.sorrisomarcado.data.DisponibilidadeRepository
import kotlinx.coroutines.launch

@Composable
fun DisponibilidadeScreen(
    repository: DisponibilidadeRepository
) {
    var dentistaId by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var horaFim by remember { mutableStateOf("") }
    var horarios by remember {
        mutableStateOf<List<Disponibilidade>>(emptyList())
    }
    var mensagem by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    fun carregarHorarios() {
        scope.launch {
            horarios = repository.listarTodas()
        }
    }

    LaunchedEffect(repository) {
        carregarHorarios()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Disponibilidade do dentista",
            style = MaterialTheme.typography.headlineSmall
        )

        Text("Cadastre os dias e horários disponíveis para atendimento.")

        OutlinedTextField(
            value = dentistaId,
            onValueChange = { dentistaId = it },
            label = { Text("ID do dentista") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = data,
            onValueChange = { data = it },
            label = { Text("Data (AAAA-MM-DD)") },
            placeholder = { Text("2026-10-25") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = horaInicio,
            onValueChange = { horaInicio = it },
            label = { Text("Horário inicial (HH:MM)") },
            placeholder = { Text("08:00") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = horaFim,
            onValueChange = { horaFim = it },
            label = { Text("Horário final (HH:MM)") },
            placeholder = { Text("09:00") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val id = dentistaId.toIntOrNull()

                if (
                    id == null ||
                    data.isBlank() ||
                    horaInicio.isBlank() ||
                    horaFim.isBlank()
                ) {
                    mensagem = "Preencha todos os campos corretamente."
                } else if (
                    !data.matches(Regex("""\d{4}-\d{2}-\d{2}""")) ||
                    !horaInicio.matches(Regex("""([01]\d|2[0-3]):[0-5]\d""")) ||
                    !horaFim.matches(Regex("""([01]\d|2[0-3]):[0-5]\d""")) ||
                    horaInicio >= horaFim
                ) {
                    mensagem = "Confira a data e os horários informados."
                } else {
                    scope.launch {
                        try {
                            repository.inserir(
                                Disponibilidade(
                                    dentistaId = id,
                                    data = data,
                                    horaInicio = horaInicio,
                                    horaFim = horaFim
                                )
                            )
                            mensagem = "Disponibilidade cadastrada!"
                            carregarHorarios()
                        } catch (e: Exception) {
                            mensagem = "Não foi possível salvar o horário."
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar disponibilidade")
        }

        if (mensagem.isNotBlank()) {
            Text(text = mensagem)
        }

        Text(
            text = "Horários cadastrados",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(horarios, key = { it.id }) { horario ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text("Dentista: ${horario.dentistaId}")
                        Text("Data: ${horario.data}")
                        Text("Horário: ${horario.horaInicio} - ${horario.horaFim}")
                        Text(
                            if (horario.disponivel) {
                                "Situação: Disponível"
                            } else {
                                "Situação: Indisponível"
                            }
                        )

                        Row {
                            TextButton(
                                onClick = {
                                    scope.launch {
                                        try {
                                            repository.alterarSituacao(
                                                horario.id,
                                                !horario.disponivel
                                            )
                                            carregarHorarios()
                                        } catch (e: Exception) {
                                            mensagem = "Erro ao alterar horário."
                                        }
                                    }
                                }
                            ) {
                                Text(
                                    if (horario.disponivel) {
                                        "Marcar indisponível"
                                    } else {
                                        "Liberar horário"
                                    }
                                )
                            }

                            TextButton(
                                onClick = {
                                    scope.launch {
                                        try {
                                            repository.excluir(horario)
                                            carregarHorarios()
                                            mensagem = "Horário excluído."
                                        } catch (e: Exception) {
                                            mensagem = "Erro ao excluir horário."
                                        }
                                    }
                                }
                            ) {
                                Text("Excluir")
                            }
                        }
                    }
                }
            }
        }
    }
}
