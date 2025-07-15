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
public class Registro {
    static java.util.ArrayList<Corredor> corredores = new java.util.ArrayList<>();
    static java.util.HashSet<Integer> numerosUsados = new java.util.HashSet<>();

    public static void registrarEquipo(Equipo[] equipos, int index) {
        if (index >= 10) {
            JOptionPane.showMessageDialog(null, "Máximo de equipos alcanzado.");
            return;
        }

        String nombre = pedirTexto("Nombre del equipo:");
        int perf = pedirEntero("Performance (0-10):", 0, 10);
        double presupuesto = pedirDecimal("Presupuesto positivo:");
        equipos[index] = new Equipo(nombre, perf, presupuesto);
    }

    public static void registrarCorredor(Equipo[] equipos, int totalEquipos) {
        if (totalEquipos == 0) {
            JOptionPane.showMessageDialog(null, "Primero registra un equipo.");
            return;
        }

        String[] opciones = new String[totalEquipos];
        for (int i = 0; i < totalEquipos; i++) {
            opciones[i] = equipos[i].id + " - " + equipos[i].nombre;
        }

        String eleccion = (String) JOptionPane.showInputDialog(null, "Seleccione equipo:", "Equipos",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        int indice = java.util.Arrays.asList(opciones).indexOf(eleccion);
        Equipo eq = equipos[indice];
        if (!eq.tieneEspacio()) {
            JOptionPane.showMessageDialog(null, "Este equipo ya tiene 2 corredores.");
            return;
        }

        String nombre = pedirTexto("Nombre del corredor:");
        int numero;
        do {
            numero = pedirEntero("Número del corredor (único):", 1, 999);
        } while (!numerosUsados.add(numero));

        int habilidad = pedirEntero("Habilidad (0–10):", 0, 10);
        int experiencia = pedirEntero("Experiencia (0–10):", 0, 10);

        Corredor nuevo = new Corredor(nombre, numero, habilidad, experiencia, eq.id);
        corredores.add(nuevo);
        eq.agregarCorredor(nuevo);
    }

    public static void asignarPrincipal(Equipo[] equipos) {
        for (Equipo eq : equipos) {
            if (eq == null) continue;
            Corredor[] c = eq.getCorredores();
            if (c[0] == null && c[1] == null) continue;

            String mensaje = "Asignar principal a " + eq.nombre + "\n";
            if (c[0] != null) mensaje += "1. " + c[0].nombre + "\n";
            if (c[1] != null) mensaje += "2. " + c[1].nombre + "\n";

            int opc = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            if (opc == 1 && c[0] != null) eq.principal = c[0];
            else if (opc == 2 && c[1] != null) eq.principal = c[1];
        }
    }

    public static void mostrarTodo(Equipo[] equipos) {
        StringBuilder sb = new StringBuilder("=== Equipos y Corredores ===\n\n");
        for (Equipo eq : equipos) {
            if (eq == null) continue;
            sb.append(eq.id).append(" - ").append(eq.nombre).append(" | Perf: ")
              .append(eq.performance).append(" | Presupuesto: ").append(eq.presupuesto).append("\n");
            for (Corredor c : eq.corredores) {
                if (c != null) {
                    sb.append("   > ").append(c.id).append(" - ").append(c.nombre)
                      .append(" (").append(c.numero).append(") H:").append(c.habilidad)
                      .append(" E:").append(c.experiencia);
                    if (eq.principal == c) sb.append(" [PRINCIPAL]");
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    static String pedirTexto(String msg) {
        String r;
        do {
            r = JOptionPane.showInputDialog(msg);
        } while (r == null || r.trim().equals(""));
        return r.trim();
    }

    static int pedirEntero(String msg, int min, int max) {
        int num = min - 1;
        while (num < min || num > max) {
            try {
                num = Integer.parseInt(JOptionPane.showInputDialog(msg));
            } catch (Exception e) {
                num = min - 1;
            }
        }
        return num;
    }

    static double pedirDecimal(String msg) {
        double num = -1;
        while (num <= 0) {
            try {
                num = Double.parseDouble(JOptionPane.showInputDialog(msg));
            } catch (Exception e) {
                num = -1;
            }
        }
        return num;
    }
}
