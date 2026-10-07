package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun EdicaoScreen(consultaId: Int, onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.consultaDao()

    var initialData by remember { mutableStateOf("") }
    var initialHorario by remember { mutableStateOf("") }
    var initialDentista by remember { mutableStateOf("") }
    var initialProcedimento by remember { mutableStateOf("") }

    var consultaOriginal by remember { mutableStateOf<Consulta?>(null) }

    LaunchedEffect(consultaId) {
        val consulta = dao.getById(consultaId)
        if (consulta != null) {
            consultaOriginal = consulta
            initialData = consulta.data
            initialHorario = consulta.horario
            initialDentista = consulta.dentista
            initialProcedimento = consulta.procedimento
        }
    }

    EdicaoScreenContent(
        initialData = initialData,
        initialHorario = initialHorario,
        initialDentista = initialDentista,
        initialProcedimento = initialProcedimento,
        onNavigateBack = onNavigateBack,
        onSave = { data, horario, dentista, procedimento ->
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
        },
        onDelete = {
            scope.launch {
                consultaOriginal?.let {
                    dao.delete(it)
                    onNavigateBack()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdicaoScreenContent(
    initialData: String,
    initialHorario: String,
    initialDentista: String,
    initialProcedimento: String,
    onNavigateBack: () -> Unit,
    onSave: (String, String, String, String) -> Unit,
    onDelete: () -> Unit
) {
    var data by remember { mutableStateOf(initialData) }
    var horario by remember { mutableStateOf(initialHorario) }
    var dentista by remember { mutableStateOf(initialDentista) }
    var procedimento by remember { mutableStateOf(initialProcedimento) }

    // Sync local state when initial values change (data loaded from DB)
    LaunchedEffect(initialData, initialHorario, initialDentista, initialProcedimento) {
        data = initialData
        horario = initialHorario
        dentista = initialDentista
        procedimento = initialProcedimento
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Consulta", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
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
                text = "Modifique os dados necessários",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = { Text("Data") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = horario,
                onValueChange = { horario = it },
                label = { Text("Horário") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = dentista,
                onValueChange = { dentista = it },
                label = { Text("Dentista") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
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
                        onSave(data, horario, dentista, procedimento)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Salvar Alterações", style = MaterialTheme.typography.labelLarge)
            }

            OutlinedButton(
                onClick = onDelete,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Excluir Consulta", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EdicaoScreenPreview() {
    SorrisomarcadoTheme {
        EdicaoScreenContent(
            initialData = "12/05/2024",
            initialHorario = "10:00",
            initialDentista = "Dr. Hélio Bentzen",
            initialProcedimento = "Limpeza",
            onNavigateBack = {},
            onSave = { _, _, _, _ -> },
            onDelete = {}
        )
    }
}
