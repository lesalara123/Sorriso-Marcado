
package com.example.sorrisomarcado.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

private val TealGreen = Color(0xFF174E49)
private val SecondaryText = Color(0xFF64736D)

enum class MainDestination(
    val label: String
) {
    Agenda("Agenda"),
    Pacientes("Pacientes"),
    Dentistas("Dentistas"),
    Mais("Mais")
}

@Composable
fun BottomNavigationBar(
    selectedDestination: MainDestination,
    onDestinationSelected: (MainDestination) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = TealGreen
    ) {
        MainDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination == destination,
                onClick = {
                    onDestinationSelected(destination)
                },
                icon = {
                    when (destination) {
                        MainDestination.Agenda -> Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = destination.label
                        )

                        MainDestination.Pacientes -> Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = destination.label
                        )

                        MainDestination.Dentistas -> Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = destination.label
                        )

                        MainDestination.Mais -> Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = destination.label
                        )
                    }
                },
                label = {
                    Text(
                        text = destination.label,
                        fontWeight = if (
                            selectedDestination == destination
                        ) {
                            FontWeight.SemiBold
                        } else {
                            FontWeight.Normal
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TealGreen,
                    selectedTextColor = TealGreen,
                    indicatorColor = Color(0xFFE2EBE2),
                    unselectedIconColor = SecondaryText,
                    unselectedTextColor = SecondaryText
                )
            )
        }
    }
}
