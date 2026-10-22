
package com.example.sorrisomarcado.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sorrisomarcado.data.Disponibilidade

@Composable
fun DisponibilidadeScreen(
    dentistaId: Int,
    disponibilidades: List<Disponibilidade>,
    onSalvar: (Disponibilidade) -> Unit
) {
    var data by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var horaFim by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Cadastrar disponibilidade")

        Text(text = "Informe os dias e horários em que o dentista poderá atender.")

        OutlinedTextField(
            value = data,
            onValueChange = {
                data = it
                mensagem = ""
            },
            label = { Text("Data (AAAA-MM-DD)") },
            placeholder = { Text("2026-10-25") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = horaInicio,
            onValueChange = {
                horaInicio = it
                mensagem = ""
            },
            label = { Text("Horário inicial (HH:MM)") },
            placeholder = { Text("08:00") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = horaFim,
            onValueChange = {
                horaFim = it
                mensagem = ""
            },
            label = { Text("Horário final (HH:MM)") },
            placeholder = { Text("12:00") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                val dataValida =
                    Regex("""\d{4}-\d{2}-\d{2}""").matches(data)

                val horaInicioValida =
                    Regex("""([01]\d|2[0-3]):[0-5]\d""")
                        .matches(horaInicio)

                val horaFimValida =
                    Regex("""([01]\d|2[0-3]):[0-5]\d""")
                        .matches(horaFim)

                when {
                    !dataValida -> {
                        mensagem = "Informe a data no formato AAAA-MM-DD."
                    }

                    !horaInicioValida || !horaFimValida -> {
                        mensagem = "Informe os horários no formato HH:MM."
                    }

                    horaFim <= horaInicio -> {
                        mensagem = "O horário final deve ser depois do inicial."
                    }

                    else -> {
                        val disponibilidade = Disponibilidade(
                            dentistaId = dentistaId,
                            data = data,
                            horaInicio = horaInicio,
                            horaFim = horaFim,
                            disponivel = true
                        )

                        onSalvar(disponibilidade)

                        data = ""
                        horaInicio = ""
                        horaFim = ""
                        mensagem = "Disponibilidade enviada para salvar!"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar disponibilidade")
        }

        if (mensagem.isNotEmpty()) {
            Text(text = mensagem)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Disponibilidades cadastradas")

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(disponibilidades) { disponibilidade ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Data: ${disponibilidade.data}")
                        Text(
                            text = "Horário: " +
                                    "${disponibilidade.horaInicio} às " +
                                    disponibilidade.horaFim
                        )
                        Text(
                            text = if (disponibilidade.disponivel) {
                                "Status: Disponível"
                            } else {
                                "Status: Indisponível"
                            }
                        )
                    }
                }
            }
        }
    }
}
