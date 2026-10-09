
package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sorrisomarcado.data.AppDatabase
import com.example.sorrisomarcado.data.Consulta
import kotlinx.coroutines.launch

private val EditTealGreen = Color(0xFF174E49)
private val EditWarmBackground = Color(0xFFF7F5EF)
private val EditDarkGreenText = Color(0xFF173D37)
private val EditSecondaryText = Color(0xFF64736D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdicaoScreen(
    consultaId: Int,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dao = remember {
        AppDatabase.getDatabase(context).consultaDao()
    }

    var data by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }
    var dentista by remember { mutableStateOf("") }
    var procedimento by remember { mutableStateOf("") }

    var carregando by remember { mutableStateOf(true) }
    var salvando by remember { mutableStateOf(false) }
    var consultaEncontrada by remember { mutableStateOf(false) }
    var mostrarConfirmacaoExclusao by remember { mutableStateOf(false) }
    var erroValidacao by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(consultaId) {
        carregando = true

        try {
            val consulta = dao.getById(consultaId)

            if (consulta != null) {
                data = consulta.data
                horario = consulta.horario
                dentista = consulta.dentista
                procedimento = consulta.procedimento
                consultaEncontrada = true
            } else {
                consultaEncontrada = false
            }
        } catch (e: Exception) {
            consultaEncontrada = false
            snackbarHostState.showSnackbar(
                "Não foi possível carregar a consulta."
            )
        } finally {
            carregando = false
        }
    }

    Scaffold(
        containerColor = EditWarmBackground,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Editar consulta",
                            fontWeight = FontWeight.SemiBold,
                            color = EditDarkGreenText
                        )
                        Text(
                            text = "Atualize os dados do atendimento",
                            style = MaterialTheme.typography.bodySmall,
                            color = EditSecondaryText
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = EditTealGreen
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EditWarmBackground
                )
            )
        }
    ) { innerPadding ->
        when {
            carregando -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = EditTealGreen)
                }
            }

            !consultaEncontrada -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Consulta não encontrada",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = EditDarkGreenText
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Ela pode ter sido removida ou não estar mais disponível.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = EditSecondaryText
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = onNavigateBack,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EditTealGreen
                        )
                    ) {
                        Text("Voltar para a agenda")
                    }
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Dados do atendimento",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = EditDarkGreenText
                            )

                            Text(
                                text = "Confira as informações antes de salvar.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = EditSecondaryText
                            )
                        }

                        OutlinedTextField(
                            value = data,
                            onValueChange = {
                                data = it
                                erroValidacao = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Data") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null
                                )
                            },
                            placeholder = { Text("Ex.: 25/10/2026") },
                            singleLine = true,
                            isError = erroValidacao && data.isBlank(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = horario,
                            onValueChange = {
                                horario = it
                                erroValidacao = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Horário") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null
                                )
                            },
                            placeholder = { Text("Ex.: 14:30") },
                            singleLine = true,
                            isError = erroValidacao && horario.isBlank(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = dentista,
                            onValueChange = {
                                dentista = it
                                erroValidacao = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Dentista") },
                            placeholder = { Text("Nome do dentista") },
                            singleLine = true,
                            isError = erroValidacao && dentista.isBlank(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = procedimento,
                            onValueChange = {
                                procedimento = it
                                erroValidacao = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Procedimento") },
                            placeholder = { Text("Ex.: Limpeza dentária") },
                            minLines = 2,
                            isError = erroValidacao && procedimento.isBlank(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        if (erroValidacao) {
                            Text(
                                text = "Preencha todos os campos para continuar.",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Button(
                        onClick = {
                            if (
                                data.isBlank() ||
                                horario.isBlank() ||
                                dentista.isBlank() ||
                                procedimento.isBlank()
                            ) {
                                erroValidacao = true
                            } else {
                                scope.launch {
                                    salvando = true

                                    try {
                                        dao.update(
                                            Consulta(
                                                id = consultaId,
                                                data = data.trim(),
                                                horario = horario.trim(),
                                                dentista = dentista.trim(),
                                                procedimento = procedimento.trim()
                                            )
                                        )

                                        onNavigateBack()
                                    } catch (e: Exception) {
                                        snackbarHostState.showSnackbar(
                                            "Não foi possível salvar as alterações."
                                        )
                                    } finally {
                                        salvando = false
                                    }
                                }
                            }
                        },
                        enabled = !salvando,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EditTealGreen
                        )
                    ) {
                        if (salvando) {
                            CircularProgressIndicator(
                                modifier = Modifier.height(22.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = "Salvar alterações",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = {
                            mostrarConfirmacaoExclusao = true
                        },
                        enabled = !salvando,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )

                        Text(
                            text = "Excluir consulta",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }

    if (mostrarConfirmacaoExclusao) {
        AlertDialog(
            onDismissRequest = {
                mostrarConfirmacaoExclusao = false
            },
            title = {
                Text("Excluir consulta?")
            },
            text = {
                Text(
                    "Essa ação removerá a consulta permanentemente. Deseja continuar?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacaoExclusao = false

                        scope.launch {
                            try {
                                val consulta = dao.getById(consultaId)

                                if (consulta != null) {
                                    dao.delete(consulta)
                                    onNavigateBack()
                                } else {
                                    snackbarHostState.showSnackbar(
                                        "A consulta não foi encontrada."
                                    )
                                }
                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar(
                                    "Não foi possível excluir a consulta."
                                )
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Excluir",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacaoExclusao = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
