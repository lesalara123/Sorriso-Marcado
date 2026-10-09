
package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sorrisomarcado.data.Paciente
import com.example.sorrisomarcado.data.PacienteDao

private val PacienteVerde = Color(0xFF174E49)
private val PacienteFundo = Color(0xFFF7F5EF)
private val PacienteTexto = Color(0xFF173D37)
private val PacienteSecundario = Color(0xFF64736D)

@Composable
fun PacientesScreen(
    pacienteDao: PacienteDao,
    onNovoPaciente: () -> Unit,
    onEditarPaciente: (Int) -> Unit
) {
    val pacientes by pacienteDao.getAll().collectAsState(initial = emptyList())
    var busca by remember { mutableStateOf("") }

    val pacientesFiltrados = pacientes.filter { paciente ->
        paciente.nome.contains(busca, ignoreCase = true) ||
                paciente.cpf.contains(busca, ignoreCase = true)
    }

    Scaffold(
        containerColor = PacienteFundo
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Pacientes",
                style = MaterialTheme.typography.headlineMedium,
                color = PacienteTexto
            )

            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Buscar por nome ou CPF") },
                singleLine = true
            )

            Button(
                onClick = onNovoPaciente,
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = PacienteVerde
                )
            ) {
                Text("Cadastrar paciente")
            }

            if (pacientesFiltrados.isEmpty()) {
                Text(
                    text = if (busca.isBlank()) {
                        "Nenhum paciente cadastrado."
                    } else {
                        "Nenhum paciente encontrado para essa busca."
                    },
                    color = PacienteSecundario
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = pacientesFiltrados,
                        key = { it.id }
                    ) { paciente ->
                        PacienteCard(
                            paciente = paciente,
                            onEditar = {
                                onEditarPaciente(paciente.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PacienteCard(
    paciente: Paciente,
    onEditar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = paciente.nome,
                style = MaterialTheme.typography.titleMedium,
                color = PacienteTexto
            )

            Text(
                text = "CPF: ${paciente.cpf}",
                color = PacienteSecundario
            )

            Text(
                text = "Telefone: ${paciente.telefone}",
                color = PacienteSecundario
            )

            if (paciente.email.isNotBlank()) {
                Text(
                    text = "E-mail: ${paciente.email}",
                    color = PacienteSecundario
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onEditar) {
                    Text(
                        text = "Editar",
                        color = PacienteVerde
                    )
                }
            }
        }
    }
}
