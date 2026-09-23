package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sorrisomarcado.data.AppDatabase
import com.example.sorrisomarcado.data.Consulta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToCadastro: () -> Unit,
    onNavigateToEdicao: (Int) -> Unit
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.consultaDao()
    val consultas by dao.getAll().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sorriso Marcado") })
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCadastro) {
                Icon(Icons.Default.Add, contentDescription = "Cadastrar Consulta")
            }
        }
    ) { innerPadding ->
        if (consultas.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nenhuma consulta marcada.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                items(consultas) { consulta ->
                    ListItem(
                        headlineContent = { Text(consulta.procedimento) },
                        supportingContent = { Text("${consulta.data} às ${consulta.horario} - Dr(a). ${consulta.dentista}") },
                        modifier = Modifier.clickable { onNavigateToEdicao(consulta.id) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
