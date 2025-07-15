/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */

import javax.swing.JOptionPane;

public class F1_Manager {
    static Equipo[] equipos = new Equipo[10];
    static int totalEquipos = 0;

    public static void main(String[] args) {
        String opcion;

        do {
            opcion = JOptionPane.showInputDialog("""
                F1 MANAGER
                1. Registrar equipo
                2. Registrar corredor
                3. Asignar corredor principal
                4. Ver equipos y corredores
                5. Simular carrera
                6. Salir
            """);

            switch (opcion) {
                case "1" -> Registro.registrarEquipo(equipos, totalEquipos++);
                case "2" -> Registro.registrarCorredor(equipos, totalEquipos);
                case "3" -> Registro.asignarPrincipal(equipos);
                case "4" -> Registro.mostrarTodo(equipos);
                case "5" -> {
                    Carrera carrera = new Carrera(equipos);
                    Podio.mostrar(carrera);
                    RecopilacionDatos.agregarCarrera(carrera);
                }
            }

        } while (!"6".equals(opcion));
    }
}
