package org.example.EjercicioAlumnos

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path

class RepositoryAlumnos {


    val diccionario = mutableMapOf<String, List<String>>();
    val FicheroAlumo = Path.of("src/main/resources/Alumnos.txt");
    val br: BufferedReader = Files.newBufferedReader(FicheroAlumo);

    fun LeerFichero(): Map<String, List<String>> {
        br.use { flujo ->
            flujo.forEachLine { linea ->
                val encabezados = linea.split(";")
                diccionario[encabezados[0]] = listOf(
                    encabezados[1], encabezados[2], encabezados[3],encabezados[4],
                    encabezados[5], encabezados[6], encabezados[7], encabezados[8]
                )

            }

        }

        return diccionario.toSortedMap();
    }

    val nuevoDiccionario = diccionario.toMutableMap()

    fun AñadirNotaFinal():Map<String, List<String>> {
        for ((apellidos, datos) in diccionario) {

            var parcial1 = datos[2].replace(",", ".").toDoubleOrNull() ?: 0.0
            var parcial2 = datos[3].replace(",", ".").toDoubleOrNull() ?: 0.0
            val ordinario1= datos[4].replace(",", ".").toDoubleOrNull() ?: 0.0
            val ordinario2=datos[5].replace(",", ".").toDoubleOrNull() ?: 0.0
            var practicas = datos[6].replace(",", ".").toDoubleOrNull() ?: 0.0
            val practicasOrdinario=datos[7].replace(",", ".").toDoubleOrNull() ?: 0.0

            if (parcial1<5){
                if (ordinario1!=0.0) {
                    parcial1=ordinario1;
                }
            }
            if(parcial2<5){
                if (ordinario2!=0.0) {
                    parcial2=ordinario2;
                }
            }
            if (practicas<5){
                if (practicasOrdinario!=0.0) {
                    practicas=practicasOrdinario;
                }
            }

            val notaFinal=(0.3 * parcial1) + (0.3 * parcial2) + (0.4 * practicas)

            val nuevaLista = datos.toMutableList()
            nuevaLista.add(String.format("%.2f", notaFinal))

            nuevoDiccionario[apellidos] = nuevaLista
        }
    nuevoDiccionario.forEach {
        println(it)}
        return nuevoDiccionario;
    }

    fun SuspensosAprobados (){

        val listaSuspensos = mutableListOf<String>()
        val listaAprobados = mutableListOf<String>()


        for ((apellidos, datos) in nuevoDiccionario) {

            val faltas = datos[1].replace("%", "").toInt()
            var parcial1 = datos[2].replace(",", ".").toDoubleOrNull() ?: 0.0
            var parcial2 = datos[3].replace(",", ".").toDoubleOrNull() ?: 0.0
            val ordinario1= datos[4].replace(",", ".").toDoubleOrNull() ?: 0.0
            val ordinario2=datos[5].replace(",", ".").toDoubleOrNull() ?: 0.0
            var practicas = datos[6].replace(",", ".").toDoubleOrNull() ?: 0.0
            val practicasOrdinario=datos[7].replace(",", ".").toDoubleOrNull() ?: 0.0
            val notaFinal=datos[8].replace(",", ".").toDouble()

            if (parcial1<5){
                if (ordinario1!=0.0) {
                    parcial1=ordinario1;
                }
            }
            if(parcial2<5){
                if (ordinario2!=0.0) {
                    parcial2=ordinario2;
                }
            }
            if (practicas<5){
                if (practicasOrdinario!=0.0) {
                    practicas=practicasOrdinario;
                }
            }

            if (faltas>=75 && parcial2>=4 && parcial1>=4 && practicas>=4 && notaFinal >=5) {
                listaAprobados.add(apellidos)
            } else {
                listaSuspensos.add(apellidos)
            }


        }
        println("Aprobados: "+listaAprobados)
        println("Suspensos: "+listaSuspensos)

    }
}