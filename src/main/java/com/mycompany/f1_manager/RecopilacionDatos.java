/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */
public class RecopilacionDatos {
    static StringBuilder historial = new StringBuilder("Carrera,Vueltas,Equipo Ganador,Corredor Ganador,Tiempo\n");

    public static void agregarCarrera(Carrera carrera) {
        if (carrera.resultados.length == 0) return;

        String[] ganador = carrera.resultados[0];
        historial.append(carrera.nombre).append(",")
                 .append(carrera.vueltas).append(",")
                 .append(ganador[0]).append(",")
                 .append(ganador[1]).append(",")
                 .append(ganador[2]).append("\n");
    }

    public static String getHistorial() {
        return historial.toString();
    }
}
