package com.example.tarea.dropDown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun dropDown() {
    var showColorMenu by remember { mutableStateOf(false) }
    var showFontMenu by remember { mutableStateOf(false) }

    val colores = listOf("Rojo", "Azul", "Verde", "Negro", "Magenta")
    val fuentes = listOf("Normal", "Serif", "Monospace", "Cursive", "SansSerif")

    var colorSeleccionado by remember { mutableStateOf(colores[0]) }
    var fuenteSeleccionada by remember { mutableStateOf(fuentes[0]) }

    var textoColor by remember { mutableStateOf(Color.Red) }
    var textoFuente by remember { mutableStateOf(FontFamily.Default) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Color")
            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.weight(1f)) {

                Button(
                    onClick = { showColorMenu = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Color")
                }

                DropdownMenu(
                    expanded = showColorMenu,
                    onDismissRequest = { showColorMenu = false }
                ) {
                    colores.forEach { color ->
                        DropdownMenuItem(
                            text = { Text(color) },
                            onClick = {
                                colorSeleccionado = color
                                showColorMenu = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Spacer(modifier = Modifier.height(20.dp))
            Text("Tipografia")

            Box(modifier = Modifier.weight(1f)) {
                Button(
                    onClick = { showFontMenu = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Fuente")
                }

                DropdownMenu(
                    expanded = showFontMenu,
                    onDismissRequest = { showFontMenu = false }
                ) {
                    fuentes.forEach { fuente ->
                        DropdownMenuItem(
                            text = { Text(fuente) },
                            onClick = {
                                fuenteSeleccionada = fuente
                                showFontMenu = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                textoColor = when (colorSeleccionado) {
                    "Rojo" -> Color.Red
                    "Azul" -> Color.Blue
                    "Verde" -> Color.Green
                    "Negro" -> Color.Black
                    "Magenta" -> Color.Magenta
                    else -> Color.Black
                }

                textoFuente = when (fuenteSeleccionada) {
                    "Normal" -> FontFamily.Default
                    "Serif" -> FontFamily.Serif
                    "Monospace" -> FontFamily.Monospace
                    "Cursive" -> FontFamily.Cursive
                    "SansSerif" -> FontFamily.SansSerif
                    else -> FontFamily.Default
                }
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text("Aplicar Cambios")
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Texto (:",
            color = textoColor,
            fontFamily = textoFuente,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

