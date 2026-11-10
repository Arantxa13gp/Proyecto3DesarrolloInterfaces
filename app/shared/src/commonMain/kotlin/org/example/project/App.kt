package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.viewModels.NotasAlumnoVM
import org.example.project.views.NotasAlumnosScreen

@Composable
fun App() {
    MaterialTheme {
        val viewModel: NotasAlumnoVM = viewModel { NotasAlumnoVM() }
        NotasAlumnosScreen(viewModel = viewModel,)
    }
}