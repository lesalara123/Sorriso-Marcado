
package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorrisomarcado.data.Paciente
import com.example.sorrisomarcado.data.PacienteDao

private val PacienteVerde = Color(0xFF174E49)
private val PacienteFundo = Color(0xFFF7F5EF)
private val PacienteTexto = Color(0xFF173D37)
private val PacienteSecundario = Color(0xFF64736D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacientesScreen(
    pacienteDao: PacienteDao,
    onNovoPaciente: () -> Unit
) {
    val pacientesFlow = remember(pacienteDao) {
        pacienteDao.getAll()
    }
    val pacientes by pacientesFlow.collectAsState(initial = emptyList())

    var pesquisa by remember { mutableStateOf("") }

    val pacientesFiltrados = pacientes.filter {
        it.nome.contains(pesquisa.trim(), ignoreCase = true) ||
                it.cpf.contains(pesquisa.trim(), ignoreCase = true)
    }

    Scaffold(
        containerColor = PacienteFundo,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pacientes",
                        color = PacienteTexto,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PacienteFundo
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PacienteFundo)
                .padding(innerPadding)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Seus pacientes",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = PacienteTexto
                )

                Text(
                    text = "${pacientes.size} paciente(s) cadastrado(s)",
                    fontSize = 14.sp,
                    color = PacienteSecundario
                )
            }

            OutlinedTextField(
                value = pesquisa,
                onValueChange = { pesquisa = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Buscar paciente") },
                placeholder = { Text("Nome ou CPF") },
                shape = RoundedCornerShape(14.dp)
            )

            Button(
                onClick = onNovoPaciente,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PacienteVerde,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Cadastrar paciente",
                    fontWeight = FontWeight.SemiBold
                )
            }

            when {
                pacientes.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Nenhum paciente cadastrado",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PacienteTexto
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Cadastre um paciente para vê-lo nesta lista.",
                            color = PacienteSecundario
                        )
                    }
                }

                pacientesFiltrados.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Nenhum resultado encontrado",
                            color = PacienteSecundario
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(
                            items = pacientesFiltrados,
                            key = { it.id }
                        ) { paciente ->
                            PacienteCard(paciente = paciente)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PacienteCard(paciente: Paciente) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = paciente.nome,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = PacienteTexto
            )

            if (paciente.cpf.isNotBlank()) {
                Text(
                    text = "CPF: ${paciente.cpf}",
                    fontSize = 14.sp,
                    color = PacienteSecundario
                )
            }

            if (paciente.telefone.isNotBlank()) {
                Text(
                    text = "Telefone: ${paciente.telefone}",
                    fontSize = 14.sp,
                    color = PacienteSecundario
                )
            }

            if (paciente.email.isNotBlank()) {
                Text(
                    text = "E-mail: ${paciente.email}",
                    fontSize = 14.sp,
                    color = PacienteSecundario
                )
            }
        }
    }
}
