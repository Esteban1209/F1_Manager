/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */

import javax.swing.JOptionPane;

public class F1_Manager {
    private static final Equipos gestorEquipos = new Equipos();
    private static final Carreras gestorCarreras = new Carreras();
    private static final GestorReportes gestorReportes = new GestorReportes();

    public static void main(String[] args) {
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                "F1 MANAGER\n" +
                "1. Registrar equipo\n" +
                "2. Registrar corredor\n" +
                "3. Asignar corredor principal\n" +
                "4. Ver equipos y corredores\n" +
                "5. Simular carrera\n" +
                "6. Generar reportes\n" +
                "7. Salir"
            );

            switch (opcion) {
                case "1" -> gestorEquipos.registrarEquipo();
                case "2" -> gestorEquipos.registrarCorredor();
                case "3" -> gestorEquipos.asignarPrincipal();
                case "4" -> gestorEquipos.mostrarEquiposCorredores();
                case "5" -> gestorCarreras.simularCarrera(gestorEquipos);
                case "6" -> gestorReportes.generarReportes(gestorEquipos, gestorCarreras);
                case "7" -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }
}