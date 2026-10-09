
package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sorrisomarcado.R

private val TealGreen = Color(0xFF174E49)
private val SageGreen = Color(0xFFA8BDA8)
private val WarmBackground = Color(0xFFF7F5EF)
private val DarkGreenText = Color(0xFF173D37)
private val SecondaryText = Color(0xFF64736D)
private val BorderColor = Color(0xFFE4E8E1)

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

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onNavigateToCadastro: () -> Unit,
    onNavigateToEdicao: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = WarmBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 16.dp,
                end = 20.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.labelLarge,
                        color = TealGreen,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "Sua agenda",
                        style = MaterialTheme.typography.headlineMedium,
                        color = DarkGreenText,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Acompanhe e organize os atendimentos.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SecondaryText
                    )
                }
            }

            item {
                Button(
                    onClick = onNavigateToCadastro,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TealGreen,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "Nova consulta",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            item {
                RowWithCounter(
                    uiState = uiState
                )
            }

            when (uiState) {
                is HomeUiState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = TealGreen
                            )
                        }
                    }
                }

                is HomeUiState.Empty -> {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            border = BorderStroke(1.dp, BorderColor),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 0.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Sua agenda começa aqui",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = DarkGreenText,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Text(
                                    text = "Cadastre uma consulta para começar a organizar os atendimentos.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = SecondaryText
                                )
                            }
                        }
                    }
                }

                is HomeUiState.Success -> {
                    items(
                        items = uiState.consultas,
                        key = { consulta -> consulta.id }
                    ) { consulta ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onNavigateToEdicao(consulta.id)
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            border = BorderStroke(1.dp, BorderColor),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 0.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = consulta.procedimento,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = DarkGreenText,
                                    fontWeight = FontWeight.Bold
                                )

                                HorizontalDivider(
                                    color = BorderColor
                                )

                                Text(
                                    text = "Data: ${consulta.data} • Horário: ${consulta.horario}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = SecondaryText
                                )

                                Text(
                                    text = "Dentista: ${consulta.dentista}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = DarkGreenText
                                )

                                Text(
                                    text = "Toque para ver ou editar",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TealGreen
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowWithCounter(
    uiState: HomeUiState
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Consultas cadastradas",
            style = MaterialTheme.typography.titleLarge,
            color = DarkGreenText,
            fontWeight = FontWeight.Bold
        )

        Surface(
            shape = RoundedCornerShape(50),
            color = SageGreen.copy(alpha = 0.35f)
        ) {
            Text(
                text = when (uiState) {
                    is HomeUiState.Success -> uiState.consultas.size.toString()
                    is HomeUiState.Empty -> "0"
                    is HomeUiState.Loading -> "…"
                },
                modifier = Modifier.padding(
                    horizontal = 11.dp,
                    vertical = 5.dp
                ),
                style = MaterialTheme.typography.labelLarge,
                color = DarkGreenText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
