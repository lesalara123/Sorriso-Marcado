package com.example.sorrisomarcado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.sorrisomarcado.ui.navigation.NavGraph
import com.example.sorrisomarcado.ui.theme.SorrisomarcadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SorrisomarcadoTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
