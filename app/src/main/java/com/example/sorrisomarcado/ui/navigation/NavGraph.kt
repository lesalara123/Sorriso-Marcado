package com.example.sorrisomarcado.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sorrisomarcado.ui.screens.CadastroScreen
import com.example.sorrisomarcado.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onNavigateToCadastro = { navController.navigate("cadastro") })
        }
        composable("cadastro") {
            CadastroScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
