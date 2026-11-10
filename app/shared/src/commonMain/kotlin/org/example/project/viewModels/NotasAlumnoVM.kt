package org.example.project.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.models.AlumnoModel
import org.example.project.states.NotasAlumnosState

class NotasAlumnosVM : ViewModel() {

    private val _uiState = MutableStateFlow(NotasAlumnosState())
    val uiState: StateFlow<NotasAlumnosState> = _uiState.asStateFlow()

    fun guardarAlumno(
        nombre: String,
        notaTeoria: Double,
        notaPracticas: Double
    ) {
        if (
            nombre.isNotBlank() &&
            notaTeoria in 0.0..10.0 &&
            notaPracticas in 0.0..10.0
        ) {
            val alumno = AlumnoModel(
                nombre = nombre,
                notaTeoria = notaTeoria,
                notaPracticas = notaPracticas
            )

            _uiState.value = _uiState.value.copy(
                alumnos = _uiState.value.alumnos + alumno
            )
        }
    }
}