package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorrisomarcado.data.Paciente
import com.example.sorrisomarcado.data.PacienteDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesPacienteScreen(
    pacienteId: Int,
    pacienteDao: PacienteDao,
    onVoltar: () -> Unit
) {
    var paciente by remember {
        mutableStateOf<Paciente?>(null)
    }

    var carregando by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(pacienteId) {
        carregando = true
        paciente = pacienteDao.getById(pacienteId)
        carregando = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detalhes do paciente")
                },
                navigationIcon = {
                    TextButton(onClick = onVoltar) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                carregando -> {
                    CircularProgressIndicator()
                }

                paciente == null -> {
                    Text("Paciente não encontrado.")

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Button(onClick = onVoltar) {
                        Text("Voltar")
                    }
                }

                else -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = paciente!!.nome,
                                fontSize = 22.sp
                            )

                            Text("CPF: ${paciente!!.cpf}")

                            Text("Telefone: ${paciente!!.telefone}")

                            Text(
                                "E-mail: ${
                                    paciente!!.email.ifBlank {
                                        "Não informado"
                                    }
                                }"
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    Button(onClick = onVoltar) {
                        Text("Voltar para pacientes")
                    }
                }
            }
        }
    }
}