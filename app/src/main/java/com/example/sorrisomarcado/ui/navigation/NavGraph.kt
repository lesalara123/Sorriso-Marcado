package com.example.sorrisomarcado.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sorrisomarcado.SorrisoApplication
import com.example.sorrisomarcado.ui.screens.CadastroScreen
import com.example.sorrisomarcado.ui.screens.EdicaoScreen
import com.example.sorrisomarcado.ui.screens.HomeScreen
import com.example.sorrisomarcado.ui.screens.HomeViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val application = context.applicationContext as SorrisoApplication
    val repository = application.repository

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(repository)
            )
            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToCadastro = { navController.navigate("cadastro") },
                onNavigateToEdicao = { id -> navController.navigate("edicao/$id") }
            )
        }
        composable("cadastro") {
            CadastroScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = "edicao/{consultaId}",
            arguments = listOf(navArgument("consultaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("consultaId") ?: 0
            EdicaoScreen(
                consultaId = id,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
