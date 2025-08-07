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

// Clase principal que gestiona el menú de la aplicación F1 Manager.
public class F1_Manager {
    // Instancia estática de la clase Equipos para gestionar equipos y corredores.
    private static final Equipos gestorEquipos = new Equipos();
    // Instancia estática de la clase Carreras para simular carreras.
    private static final Carreras gestorCarreras = new Carreras();
    // Instancia estática de la clase GestorReportes para generar reportes.
    private static final GestorReportes gestorReportes = new GestorReportes();

    // Método principal (punto de entrada del programa).
    public static void main(String[] args) {
        // Bucle infinito para mantener el menú activo hasta que el usuario elija salir.
        while (true) {
            // Muestra un cuadro de diálogo con las opciones del menú y recibe la elección del usuario.
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

            // Switch para manejar las opciones del usuario.
            switch (opcion) {
                case "1" -> gestorEquipos.registrarEquipo(); // Llama al método para registrar un nuevo equipo.
                case "2" -> gestorEquipos.registrarCorredor(); // Llama al método para registrar un corredor.
                case "3" -> gestorEquipos.asignarPrincipal(); // Asigna un corredor como principal en un equipo.
                case "4" -> gestorEquipos.mostrarEquiposCorredores(); // Muestra la lista de equipos y corredores.
                case "5" -> gestorCarreras.simularCarrera(gestorEquipos); // Simula una carrera usando los equipos registrados.
                case "6" -> gestorReportes.generarReportes(gestorEquipos, gestorCarreras); // Genera reportes basados en datos de equipos y carreras.
                case "7" -> System.exit(0); // Termina la ejecución del programa.
                default -> JOptionPane.showMessageDialog(null, "Opción inválida"); // Mensaje de error si la opción no es válida.
            }
        }
    }
}