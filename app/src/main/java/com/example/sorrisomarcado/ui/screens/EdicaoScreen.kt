package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sorrisomarcado.data.AppDatabase
import com.example.sorrisomarcado.data.Consulta
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdicaoScreen(consultaId: Int, onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.consultaDao()

    var data by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }
    var dentista by remember { mutableStateOf("") }
    var procedimento by remember { mutableStateOf("") }

    var consultaOriginal by remember { mutableStateOf<Consulta?>(null) }

    LaunchedEffect(consultaId) {
        val consulta = dao.getById(consultaId)
        if (consulta != null) {
            consultaOriginal = consulta
            data = consulta.data
            horario = consulta.horario
            dentista = consulta.dentista
            procedimento = consulta.procedimento
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Consulta") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = { Text("Data") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = horario,
                onValueChange = { horario = it },
                label = { Text("Horário") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = dentista,
                onValueChange = { dentista = it },
                label = { Text("Dentista") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = procedimento,
                onValueChange = { procedimento = it },
                label = { Text("Procedimento") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (data.isNotBlank() && horario.isNotBlank() && dentista.isNotBlank() && procedimento.isNotBlank()) {
                        scope.launch {
                            dao.update(
                                Consulta(
                                    id = consultaId,
                                    data = data,
                                    horario = horario,
                                    dentista = dentista,
                                    procedimento = procedimento
                                )
                            )
                            onNavigateBack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Alterações")
            }

            OutlinedButton(
                onClick = {
                    scope.launch {
                        consultaOriginal?.let {
                            dao.delete(it)
                            onNavigateBack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Excluir Consulta")
            }
        }
    }
}
