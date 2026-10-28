package com.example.sorrisomarcado.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorrisomarcado.data.Paciente
import com.example.sorrisomarcado.data.PacienteRepository
import kotlinx.coroutines.launch

private val CadastroPacienteVerde = Color(0xFF174E49)
private val CadastroPacienteFundo = Color(0xFFF7F5EF)
private val CadastroPacienteTexto = Color(0xFF173D37)
private val CadastroPacienteSecundario = Color(0xFF64736D)

// Valida o CPF, incluindo os dígitos verificadores.
private fun cpfValido(cpf: String): Boolean {
    val numeros = cpf.filter { it.isDigit() }

    if (numeros.length != 11) return false
    if (numeros.all { it == numeros[0] }) return false

    fun calcularDigito(base: String, pesos: IntArray): Int {
        val soma = base.mapIndexed { index, caractere ->
            caractere.digitToInt() * pesos[index]
        }.sum()

        val resto = soma % 11
        return if (resto < 2) 0 else 11 - resto
    }

    val primeiroDigito = calcularDigito(
        numeros.substring(0, 9),
        intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2)
    )

    val segundoDigito = calcularDigito(
        numeros.substring(0, 9) + primeiroDigito,
        intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
    )

    return numeros[9].digitToInt() == primeiroDigito &&
            numeros[10].digitToInt() == segundoDigito
}

// Aceita telefones brasileiros com 10 ou 11 dígitos.
private fun telefoneValido(telefone: String): Boolean {
    val numeros = telefone.filter { it.isDigit() }
    return numeros.length == 10 || numeros.length == 11
}

// O e-mail é opcional, mas precisa ter formato válido quando preenchido.
private fun emailValido(email: String): Boolean {
    if (email.isBlank()) return true

    return email.trim().matches(
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroPacienteScreen(
    repository: PacienteRepository,
    onNavigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var tentouSalvar by remember { mutableStateOf(false) }
    var salvando by remember { mutableStateOf(false) }

    val cpfEstaValido = cpfValido(cpf)
    val telefoneEstaValido = telefoneValido(telefone)
    val emailEstaValido = emailValido(email)

    Scaffold(
        containerColor = CadastroPacienteFundo,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Novo paciente",
                        color = CadastroPacienteTexto,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !salvando
                    ) {
                        Text(
                            text = "Voltar",
                            color = CadastroPacienteVerde
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CadastroPacienteFundo
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CadastroPacienteFundo)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Cadastro de paciente",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = CadastroPacienteTexto
                )

                Text(
                    text = "Informe os dados para registrar o paciente.",
                    fontSize = 13.sp,
                    color = CadastroPacienteSecundario
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 1.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(9.dp)
                ) {

                    Text(
                        text = "Dados pessoais",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CadastroPacienteTexto
                    )

                    // Nome do paciente
                    OutlinedTextField(
                        value = nome,
                        onValueChange = { nome = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Nome completo *") },
                        placeholder = { Text("Nome do paciente") },
                        singleLine = true,
                        enabled = !salvando,
                        isError = tentouSalvar && nome.isBlank(),
                        shape = RoundedCornerShape(12.dp),
                        colors = cadastroPacienteCampoCores()
                    )

                    // CPF
                    OutlinedTextField(
                        value = cpf,
                        onValueChange = { cpf = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("CPF *") },
                        placeholder = { Text("000.000.000-00") },
                        singleLine = true,
                        enabled = !salvando,
                        isError = tentouSalvar && !cpfValido(cpf),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        shape = RoundedCornerShape(12.dp),
                        colors = cadastroPacienteCampoCores()
                    )

                    if (tentouSalvar && !cpfValido(cpf)) {
                        Text(
                            text = "Informe um CPF válido.",
                            color = androidx.compose.material3.MaterialTheme
                                .colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    // Telefone
                    OutlinedTextField(
                        value = telefone,
                        onValueChange = { telefone = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Telefone *") },
                        placeholder = { Text("(00) 00000-0000") },
                        singleLine = true,
                        enabled = !salvando,
                        isError = tentouSalvar && !telefoneValido(telefone),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),
                        shape = RoundedCornerShape(12.dp),
                        colors = cadastroPacienteCampoCores()
                    )

                    if (tentouSalvar && !telefoneValido(telefone)) {
                        Text(
                            text = "Informe um telefone com DDD e número válido.",
                            color = androidx.compose.material3.MaterialTheme
                                .colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    // E-mail
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("E-mail") },
                        placeholder = { Text("paciente@exemplo.com") },
                        singleLine = true,
                        enabled = !salvando,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        isError = tentouSalvar && !emailValido(email),
                        shape = RoundedCornerShape(12.dp),
                        colors = cadastroPacienteCampoCores()
                    )

                    if (tentouSalvar && !emailValido(email)) {
                        Text(
                            text = "Informe um e-mail válido.",
                            color = androidx.compose.material3.MaterialTheme
                                .colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text = "* Campos obrigatórios",
                        fontSize = 12.sp,
                        color = CadastroPacienteSecundario
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Button(
                        onClick = {
                            tentouSalvar = true

                            if (salvando) return@Button

                            if (
                                nome.isBlank() ||
                                !cpfValido(cpf) ||
                                !telefoneValido(telefone) ||
                                !emailValido(email)
                            ) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Confira os campos e corrija os dados inválidos."
                                    )
                                }
                                return@Button
                            }

                            scope.launch {
                                salvando = true

                                try {
                                    val cpfLimpo = cpf.filter { it.isDigit() }
                                    val telefoneLimpo =
                                        telefone.filter { it.isDigit() }

                                    val pacienteExistente =
                                        repository.getPacienteByCpf(cpfLimpo)

                                    if (pacienteExistente != null) {
                                        snackbarHostState.showSnackbar(
                                            "Já existe um paciente cadastrado com esse CPF."
                                        )
                                        return@launch
                                    }

                                    repository.insertPaciente(
                                        Paciente(
                                            nome = nome.trim(),
                                            cpf = cpfLimpo,
                                            telefone = telefoneLimpo,
                                            email = email.trim()
                                        )
                                    )

                                    onNavigateBack()

                                } catch (e: Exception) {
                                    snackbarHostState.showSnackbar(
                                        "Não foi possível salvar o paciente. Tente novamente."
                                    )
                                } finally {
                                    salvando = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = !salvando,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CadastroPacienteVerde,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = if (salvando) {
                                "Salvando..."
                            } else {
                                "Salvar paciente"
                            },
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun cadastroPacienteCampoCores() =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = CadastroPacienteVerde,
        focusedLabelColor = CadastroPacienteVerde,
        cursorColor = CadastroPacienteVerde,
        unfocusedBorderColor = Color(0xFFE4E8E1),
        unfocusedLabelColor = CadastroPacienteSecundario,
        focusedTextColor = CadastroPacienteTexto,
        unfocusedTextColor = CadastroPacienteTexto,
        errorBorderColor = androidx.compose.material3.MaterialTheme
            .colorScheme.error,
        errorLabelColor = androidx.compose.material3.MaterialTheme
            .colorScheme.error
    )