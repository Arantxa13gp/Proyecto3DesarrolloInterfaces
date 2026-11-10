package org.example.project.states

import org.example.project.models.AlumnoModel

data class NotasAlumnosState(
    val alumnos: List<AlumnoModel> = emptyList()
)