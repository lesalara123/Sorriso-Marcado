
package com.example.sorrisomarcado.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.sorrisomarcado.SorrisoApplication
import com.example.sorrisomarcado.data.PacienteRepository
import com.example.sorrisomarcado.ui.components.BottomNavigationBar
import com.example.sorrisomarcado.ui.components.MainDestination
import com.example.sorrisomarcado.ui.screens.CadastroPacienteScreen
import com.example.sorrisomarcado.ui.screens.CadastroScreen
import com.example.sorrisomarcado.ui.screens.EdicaoPacienteScreen
import com.example.sorrisomarcado.ui.screens.EdicaoScreen
import com.example.sorrisomarcado.ui.screens.HomeScreen
import com.example.sorrisomarcado.ui.screens.PacientesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val application = context.applicationContext as SorrisoApplication

    val repository = application.repository
    val pacienteDao = application.database.pacienteDao()

    val pacienteRepository = remember(pacienteDao) {
        PacienteRepository(pacienteDao)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        "home",
        "pacientes"
    )

    val selectedDestination = when (currentRoute) {
        "pacientes" -> MainDestination.Pacientes
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

                            MainDestination.Dentistas,
                            MainDestination.Mais -> {
                                // Implementaremos essas telas depois.
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
            composable("home") {
                val homeViewModel:
                        com.example.sorrisomarcado.ui.screens.HomeViewModel =
                    viewModel(
                        factory = com.example.sorrisomarcado.ui.screens.HomeViewModel
                            .provideFactory(repository)
                    )

                HomeScreen(
                    viewModel = homeViewModel,
                    onNavigateToCadastro = {
                        navController.navigate("cadastro")
                    },
                    onNavigateToEdicao = { id ->
                        navController.navigate("edicao/$id")
                    }
                )
            }

            composable("cadastro") {
                CadastroScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = "edicao/{consultaId}",
                arguments = listOf(
                    navArgument("consultaId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("consultaId") ?: 0

                EdicaoScreen(
                    consultaId = id,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable("pacientes") {
                PacientesScreen(
                    pacienteDao = pacienteDao,
                    onNovoPaciente = {
                        navController.navigate("cadastroPaciente")
                    },
                    onEditarPaciente = { id ->
                        navController.navigate("edicaoPaciente/$id")
                    }
                )
            }

            composable("cadastroPaciente") {
                CadastroPacienteScreen(
                    repository = pacienteRepository,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

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
        }
    }
}
