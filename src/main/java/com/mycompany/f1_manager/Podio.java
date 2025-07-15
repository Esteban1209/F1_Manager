/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Podio {
    public static void mostrar(Carrera carrera) {
        StringBuilder podio = new StringBuilder("===== PODIO FINAL =====\n");
        podio.append(carrera.nombre).append(" - ").append(carrera.vueltas).append(" Vueltas\n\n");

        for (int i = 0; i < Math.min(3, carrera.resultados.length); i++) {
            podio.append((i + 1)).append("º ")
                 .append(carrera.resultados[i][0]).append(" - ")
                 .append(carrera.resultados[i][1]).append("\n");
        }

        podio.append("\n¡Gran Premio Finalizado!");
        JOptionPane.showMessageDialog(null, podio.toString());
    }
}