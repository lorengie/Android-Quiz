package com.example.tarea.calculadora_resistencias

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color



@Composable
fun CalculadoraResistencia() {
    val colores = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val multiplicadores = listOf("Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val tolerancias = listOf("Dorado", "Plateado", "Ninguno")

    var banda1 by remember { mutableStateOf(colores[0]) }
    var banda2 by remember { mutableStateOf(colores[0]) }
    var banda3 by remember { mutableStateOf(multiplicadores[0]) }
    var tolerancia by remember { mutableStateOf(tolerancias[0]) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Selecciona las bandas de la resistencia [:")

            DropdownSelectorSimple("Banda 1", colores, banda1) { banda1 = it }
            DropdownSelectorSimple("Banda 2", colores, banda2) { banda2 = it }
            DropdownSelectorSimple("Multiplicador", multiplicadores, banda3) { banda3 = it }
            DropdownSelectorSimple("Tolerancia", tolerancias, tolerancia) { tolerancia = it }

            Spacer(modifier = Modifier.height(16.dp))


            Text("El valor de la resistencia es de: ")
        }
    }

}

@Composable
fun DropdownSelectorSimple(
    label: String,
    opciones: List<String>,
    seleccionado: String,
    onSeleccionado: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    Column {
        Text(label)
        Box(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { expandido = true }
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            Text(seleccionado)
        }
        DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onSeleccionado(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}


