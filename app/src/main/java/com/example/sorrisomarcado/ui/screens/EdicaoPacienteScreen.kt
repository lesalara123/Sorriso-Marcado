
package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorrisomarcado.data.Paciente
import com.example.sorrisomarcado.data.PacienteRepository
import kotlinx.coroutines.launch

private val EdicaoPacienteVerde = Color(0xFF174E49)
private val EdicaoPacienteFundo = Color(0xFFF7F5EF)
private val EdicaoPacienteTexto = Color(0xFF173D37)
private val EdicaoPacienteSecundario = Color(0xFF64736D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdicaoPacienteScreen(
    pacienteId: Int,
    repository: PacienteRepository,
    onNavigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var pacienteOriginal by remember {
        mutableStateOf<Paciente?>(null)
    }

    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var carregando by remember { mutableStateOf(true) }
    var salvando by remember { mutableStateOf(false) }

    LaunchedEffect(pacienteId) {
        carregando = true

        try {
            val paciente = repository.getPacienteById(pacienteId)

            if (paciente != null) {
                pacienteOriginal = paciente
                nome = paciente.nome
                cpf = paciente.cpf
                telefone = paciente.telefone
                email = paciente.email
            } else {
                snackbarHostState.showSnackbar(
                    "Paciente não encontrado."
                )
            }
        } catch (e: Exception) {
            snackbarHostState.showSnackbar(
                "Não foi possível carregar o paciente."
            )
        } finally {
            carregando = false
        }
    }

    Scaffold(
        containerColor = EdicaoPacienteFundo,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Editar paciente",
                        color = EdicaoPacienteTexto,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    TextButton(onClick = onNavigateBack) {
                        Text(
                            text = "Voltar",
                            color = EdicaoPacienteVerde
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EdicaoPacienteFundo
                )
            )
        }
    ) { innerPadding ->
        if (carregando) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = EdicaoPacienteVerde
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("Carregando dados do paciente...")
            }
        } else if (pacienteOriginal == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Não foi possível encontrar este paciente.",
                    color = EdicaoPacienteTexto
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onNavigateBack) {
                    Text("Voltar para pacientes")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(EdicaoPacienteFundo)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Dados pessoais",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = EdicaoPacienteTexto
                )

                Text(
                    text = "Atualize os dados e salve as alterações.",
                    fontSize = 14.sp,
                    color = EdicaoPacienteSecundario
                )

                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nome completo") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp)
                )

                OutlinedTextField(
                    value = cpf,
                    onValueChange = { cpf = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("CPF") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = RoundedCornerShape(14.dp)
                )

                OutlinedTextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Telefone") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    shape = RoundedCornerShape(14.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("E-mail (opcional)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = {
                        val nomeFinal = nome.trim()
                        val cpfFinal = cpf.trim()
                        val telefoneFinal = telefone.trim()
                        val emailFinal = email.trim()

                        when {
                            nomeFinal.isBlank() -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Informe o nome do paciente."
                                    )
                                }
                            }

                            cpfFinal.isBlank() -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Informe o CPF do paciente."
                                    )
                                }
                            }

                            telefoneFinal.isBlank() -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Informe o telefone do paciente."
                                    )
                                }
                            }

                            emailFinal.isNotBlank() &&
                                    !android.util.Patterns.EMAIL_ADDRESS
                                        .matcher(emailFinal)
                                        .matches() -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Informe um e-mail válido."
                                    )
                                }
                            }

                            else -> {
                                scope.launch {
                                    salvando = true

                                    try {
                                        val existente =
                                            repository.getPacienteByCpf(cpfFinal)

                                        if (
                                            existente != null &&
                                            existente.id != pacienteId
                                        ) {
                                            snackbarHostState.showSnackbar(
                                                "Este CPF já está cadastrado."
                                            )
                                            return@launch
                                        }

                                        repository.updatePaciente(
                                            Paciente(
                                                id = pacienteId,
                                                nome = nomeFinal,
                                                cpf = cpfFinal,
                                                telefone = telefoneFinal,
                                                email = emailFinal
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
                        }
                    },
                    enabled = !salvando,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EdicaoPacienteVerde,
                        contentColor = Color.White
                    )
                ) {
                    if (salvando) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .height(20.dp),
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !salvando
                    ) {
                        Text(
                            text = "Cancelar",
                            color = EdicaoPacienteSecundario
                        )
                    }
                }
            }
        }
    }
}
