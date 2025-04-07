package com.example.tarea.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun screenB(
    navController: NavController,
    usuarios: List<Triple<String, String, String>>
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFD7B5E7)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Visualización", fontSize = 28.sp, color = Color(0xFF3621F3))
            Spacer(modifier = Modifier.height(16.dp))

            usuarios.forEachIndexed { index, usuario ->
                Text("Registro ${index + 1}", fontSize = 20.sp, color = Color(0xFF8018C0))
                Text("Nombre: ${usuario.first}")
                Text("Correo: ${usuario.second}")
                Text("Profesión: ${usuario.third}")
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("screen1") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3621F3),
                    contentColor = Color.White
                )
            ) {
                Text("Volver a Pantalla 1")
            }

        }
    }
}