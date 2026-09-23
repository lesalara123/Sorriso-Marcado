package com.example.sorrisomarcado.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sorrisomarcado.ui.screens.CadastroScreen
import com.example.sorrisomarcado.ui.screens.EdicaoScreen
import com.example.sorrisomarcado.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
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
