import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun CalculadoraResistencias() {
    val colores = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val multiplicadores = listOf("Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val tolerancias = listOf("Dorado", "Plateado", "Ninguno")

    var banda1 by remember { mutableStateOf(colores[0]) }
    var banda2 by remember { mutableStateOf(colores[0]) }
    var banda3 by remember { mutableStateOf(multiplicadores[0]) }
    var tolerancia by remember { mutableStateOf(tolerancias[0]) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        color = Color(0xFFD7ECDA)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calculadora de Resistencias",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF2A45D3))
            )

            Spacer(modifier = Modifier.height(20.dp))

            DesplegableColores("Banda 1", colores, banda1) { banda1 = it }
            DesplegableColores("Banda 2", colores, banda2) { banda2 = it }
            DesplegableColores("Multiplicador", multiplicadores, banda3) { banda3 = it }
            DesplegableColores("Tolerancia", tolerancias, tolerancia) { tolerancia = it }

            Spacer(modifier = Modifier.height(20.dp))

            ResistenciaVisual(banda1, banda2, banda3, tolerancia)

            Spacer(modifier = Modifier.height(20.dp))

            val resultado = calcularValor(banda1, banda2, banda3, tolerancia)
            Text("Resultado: $resultado", color = Color(0xFF333333), style = MaterialTheme.typography.titleLarge)
        }
    }
}
@Composable
fun DesplegableColores(
    label: String,
    opciones: List<String>,
    seleccionado: String,
    onSeleccionado: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFB0BEC5), RoundedCornerShape(12.dp))
                .clickable { expandido = true }
                .background(Color(0xFFE3F2FD), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = seleccionado,
                    color = Color(0xFF263238),
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = "Desplegar",
                    tint = Color(0xFF263238)
                )
            }
        }
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
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

@Composable
fun ResistenciaVisual(b1: String, b2: String, b3: String, tol: String) {
    val colorMap = mapOf(
        "Negro" to Color.Black, "Marrón" to Color(0xFF8B4513), "Rojo" to Color.Red,
        "Naranja" to Color(0xFFFFA500), "Amarillo" to Color.Yellow, "Verde" to Color.Green,
        "Azul" to Color.Blue, "Violeta" to Color(0xFF8A2BE2), "Gris" to Color.Gray,
        "Blanco" to Color.White, "Dorado" to Color(0xFFDAC73C), "Plateado" to Color(0xFFDEDEDE),
        "Ninguno" to Color(0xFFCACAD0)
    )

    val anchoBanda = 20.dp

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(8.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xFFCACAD0))
                .border(2.dp, Color.DarkGray, RoundedCornerShape(25.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(b1, b2, b3, tol).forEach {
                    Box(
                        modifier = Modifier
                            .width(anchoBanda)
                            .height(40.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(colorMap[it] ?: Color.Black)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(8.dp)
                .background(Color.DarkGray)
        )
    }
}

fun calcularValor(b1: String, b2: String, b3: String, tol: String): String {
    val valores = mapOf(
        "Negro" to 0, "Marrón" to 1, "Rojo" to 2, "Naranja" to 3, "Amarillo" to 4,
        "Verde" to 5, "Azul" to 6, "Violeta" to 7, "Gris" to 8, "Blanco" to 9
    )

    val multiplicador = mapOf(
        "Amarillo" to 10000.0,
        "Verde" to 100000.0,
        "Azul" to 1000000.0,
        "Violeta" to 10000000.0,
        "Gris" to 100000000.0,
        "Blanco" to 1000000000.0
    )

    val toleranciaTexto = mapOf(
        "Dorado" to "±5%", "Plateado" to "±10%", "Ninguno" to "±20%"
    )

    val num1 = valores[b1] ?: 0
    val num2 = valores[b2] ?: 0
    val multi = multiplicador[b3] ?: 1.0
    val resistencia = ((num1 * 10) + num2) * multi

    return "${num1}${num2} × ${multi.toInt()} = $resistencia Ω ${toleranciaTexto[tol]}"
}
