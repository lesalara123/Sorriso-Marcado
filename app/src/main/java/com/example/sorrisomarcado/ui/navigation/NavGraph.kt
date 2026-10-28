
package com.example.sorrisomarcado.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument

import com.example.sorrisomarcado.SorrisoApplication
import com.example.sorrisomarcado.data.DisponibilidadeRepository
import com.example.sorrisomarcado.data.PacienteRepository
import com.example.sorrisomarcado.ui.components.BottomNavigationBar
import com.example.sorrisomarcado.ui.components.MainDestination
import com.example.sorrisomarcado.ui.screens.CadastroPacienteScreen
import com.example.sorrisomarcado.ui.screens.CadastroScreen
import com.example.sorrisomarcado.ui.screens.DetalhesPacienteScreen
import com.example.sorrisomarcado.ui.screens.EdicaoPacienteScreen
import com.example.sorrisomarcado.ui.screens.EdicaoScreen
import com.example.sorrisomarcado.ui.screens.HomeScreen
import com.example.sorrisomarcado.ui.screens.PacientesScreen
import com.example.sorrisomarcado.DisponibilidadeScreen

@Composable
fun NavGraph(navController: NavHostController) {

    val context = LocalContext.current

    val application =
        context.applicationContext as SorrisoApplication

    val pacienteDao = application.database.pacienteDao()

    val pacienteRepository = remember(pacienteDao) {
        PacienteRepository(pacienteDao)
    }

    val disponibilidadeDao =
        application.database.disponibilidadeDao()

    val disponibilidadeRepository =
        remember(disponibilidadeDao) {
            DisponibilidadeRepository(disponibilidadeDao)
        }

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        "home",
        "pacientes",
        "disponibilidade"
    )

    val selectedDestination = when (currentRoute) {
        "pacientes" -> MainDestination.Pacientes
        "disponibilidade" -> MainDestination.Dentistas
        else -> MainDestination.Agenda
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    selectedDestination = selectedDestination,
                    onDestinationSelected = { destination ->
                        when (destination) {

                            MainDestination.Agenda -> {
                                navController.navigate("home") {
                                    launchSingleTop = true
                                    popUpTo("home") {
                                        inclusive = false
                                    }
                                }
                            }

                            MainDestination.Pacientes -> {
                                navController.navigate("pacientes") {
                                    launchSingleTop = true
                                    popUpTo("home") {
                                        inclusive = false
                                    }
                                }
                            }

                            MainDestination.Dentistas -> {
                                navController.navigate("disponibilidade") {
                                    launchSingleTop = true
                                }
                            }

                            MainDestination.Mais -> {
                                // Esta opção será implementada depois.
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {

            // Tela inicial
            composable("home") {
                HomeScreen(
                    onNavigateToCadastro = {
                        navController.navigate("cadastro")
                    },
                    onNavigateToEdicao = { id ->
                        navController.navigate("edicao/$id")
                    }
                )
            }

            // Cadastro de consulta
            composable("cadastro") {
                CadastroScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            // Edição de consulta
            composable(
                route = "edicao/{consultaId}",
                arguments = listOf(
                    navArgument("consultaId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val id =
                    backStackEntry.arguments?.getInt("consultaId") ?: 0

                EdicaoScreen(
                    consultaId = id,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            // Lista de pacientes
            composable("pacientes") {
                PacientesScreen(
                    pacienteDao = pacienteDao,
                    onNovoPaciente = {
                        navController.navigate("cadastroPaciente")
                    },
                    onEditarPaciente = { id ->
                        navController.navigate("edicaoPaciente/$id")
                    },
                    onVerDetalhes = { id ->
                        navController.navigate("detalhesPaciente/$id")
                    }
                )
            }

            // Cadastro de paciente
            composable("cadastroPaciente") {
                CadastroPacienteScreen(
                    repository = pacienteRepository,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            // Edição de paciente
            composable(
                route = "edicaoPaciente/{pacienteId}",
                arguments = listOf(
                    navArgument("pacienteId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val pacienteId =
                    backStackEntry.arguments?.getInt("pacienteId") ?: 0

                EdicaoPacienteScreen(
                    pacienteId = pacienteId,
                    repository = pacienteRepository,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            // Detalhes do paciente
            composable(
                route = "detalhesPaciente/{pacienteId}",
                arguments = listOf(
                    navArgument("pacienteId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val pacienteId =
                    backStackEntry.arguments?.getInt("pacienteId") ?: 0

                DetalhesPacienteScreen(
                    pacienteId = pacienteId,
                    pacienteDao = pacienteDao,
                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            // Disponibilidade dos dentistas
            composable("disponibilidade") {
                DisponibilidadeScreen(
                    repository = disponibilidadeRepository
                )
            }
                )
            }
        }
    }
}

@Composable
fun DisponibilidadeScreen(
    repository: DisponibilidadeRepository
) {
    TODO("Not yet implemented")
}