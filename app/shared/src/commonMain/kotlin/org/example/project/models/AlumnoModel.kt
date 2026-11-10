package org.example.project.models

data class AlumnoModel(
    val nombre: String,
    val notaTeoria: Double,
    val notaPracticas: Double
) {
    fun calcularNotaFinal(): Double {
        return notaTeoria * 0.6 + notaPracticas * 0.4
    }

    fun estaAprobado(): Boolean {
        return calcularNotaFinal() >= 5.0
    }
}