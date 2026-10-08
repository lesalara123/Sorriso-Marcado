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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sorrisomarcado.R
import com.example.sorrisomarcado.data.Consulta

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToCadastro: () -> Unit,
    onNavigateToEdicao: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        onNavigateToCadastro = onNavigateToCadastro,
        onNavigateToEdicao = onNavigateToEdicao
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onNavigateToCadastro: () -> Unit,
    onNavigateToEdicao: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.home_title)) })
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCadastro) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.home_add_consultation)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (uiState) {
                is HomeUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is HomeUiState.Empty -> {
                    Text(
                        text = stringResource(R.string.home_empty_list),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is HomeUiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.consultas) { consulta ->
                            ListItem(
                                headlineContent = { Text(consulta.procedimento) },
                                supportingContent = {
                                    Text(
                                        stringResource(
                                            R.string.consultation_details,
                                            consulta.data,
                                            consulta.horario,
                                            consulta.dentista
                                        )
                                    )
                                },
                                modifier = Modifier.clickable { onNavigateToEdicao(consulta.id) }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}
