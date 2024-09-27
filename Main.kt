package org.example.EjercicioAlumnos

fun main() {
    val repo=RepositoryAlumnos()
    val diccionario=repo.LeerFichero();
    repo.AñadirNotaFinal()
    repo.SuspensosAprobados()
}

