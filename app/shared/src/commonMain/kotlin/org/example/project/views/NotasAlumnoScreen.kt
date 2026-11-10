package org.example.project.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.viewmodels.NotasAlumnosVM

@Composable
fun NotasAlumnosScreen(
    viewModel: NotasAlumnosVM = viewModel(),
    format: String.(Double) -> Unit
) {
    val estado by viewModel.uiState.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var notaTeoria by remember { mutableStateOf("") }
    var notaPracticas by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Registro de notas")

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del alumno") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notaTeoria,
            onValueChange = { notaTeoria = it },
            label = { Text("Nota de teoría") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notaPracticas,
            onValueChange = { notaPracticas = it },
            label = { Text("Nota de prácticas") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val teoria = notaTeoria.toDoubleOrNull()
                val practicas = notaPracticas.toDoubleOrNull()

                if (
                    nombre.isNotBlank() &&
                    teoria != null &&
                    practicas != null &&
                    teoria in 0.0..10.0 &&
                    practicas in 0.0..10.0
                ) {
                    viewModel.guardarAlumno(
                        nombre = nombre,
                        notaTeoria = teoria,
                        notaPracticas = practicas
                    )

                    nombre = ""
                    notaTeoria = ""
                    notaPracticas = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar alumno")
        }

        Text(text = "Lista de alumnos")

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(estado.alumnos) { alumno ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Nombre: ${alumno.nombre}")

                        Text(text = "Nota de teoría: ${alumno.notaTeoria}")

                        Text(text = "Nota de prácticas: ${alumno.notaPracticas}")

                        Text(
                            text = "Nota final: ${
                                "%.2f".format(alumno.calcularNotaFinal())
                            }"
                        )

                        Text(
                            text = if (alumno.estaAprobado()) {
                                "✅ APROBADO"
                            } else {
                                "❌ SUSPENSO"
                            }
                        )
                    }
                }
            }
        }
    }
}