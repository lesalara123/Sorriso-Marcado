package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.layout.*
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
fun CadastroScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.consultaDao()

    var data by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }
    var dentista by remember { mutableStateOf("") }
    var procedimento by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Nova Consulta") })
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
                            dao.insert(
                                Consulta(
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
                Text("Salvar Consulta")
            }
        }
    }
}
