package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sorrisomarcado.data.AppDatabase
import com.example.sorrisomarcado.data.Consulta
import com.example.sorrisomarcado.ui.theme.SorrisomarcadoTheme
import kotlinx.coroutines.launch

@Composable
fun CadastroScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.consultaDao()

    CadastroScreenContent(
        onNavigateBack = onNavigateBack,
        onSave = { data, horario, dentista, procedimento ->
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreenContent(
    onNavigateBack: () -> Unit,
    onSave: (String, String, String, String) -> Unit,
    initialData: String = "",
    initialHorario: String = "",
    initialDentista: String = "",
    initialProcedimento: String = ""
) {
    var data by remember { mutableStateOf(initialData) }
    var horario by remember { mutableStateOf(initialHorario) }
    var dentista by remember { mutableStateOf(initialDentista) }
    var procedimento by remember { mutableStateOf(initialProcedimento) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nova Consulta", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Preencha os dados da consulta",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = { Text("Data (Ex: 10/10/2024)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = horario,
                onValueChange = { horario = it },
                label = { Text("Horário (Ex: 14:00)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = dentista,
                onValueChange = { dentista = it },
                label = { Text("Nome do Dentista") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = procedimento,
                onValueChange = { procedimento = it },
                label = { Text("Procedimento Odontológico") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (data.isNotBlank() && horario.isNotBlank() && dentista.isNotBlank() && procedimento.isNotBlank()) {
                        onSave(data, horario, dentista, procedimento)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Salvar Consulta", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    SorrisomarcadoTheme {
        CadastroScreenContent(
            onNavigateBack = {},
            onSave = { _, _, _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenFilledPreview() {
    SorrisomarcadoTheme {
        CadastroScreenContent(
            onNavigateBack = {},
            onSave = { _, _, _, _ -> },
            initialData = "10/10/2024",
            initialHorario = "14:00",
            initialDentista = "Dr. Hélio Bentzen",
            initialProcedimento = "Limpeza"
        )
    }
}
