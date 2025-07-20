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

public class Equipos {
    private final Equipo[] equipos = new Equipo[10];
    private int totalEquipos = 0;

    public void registrarEquipo() {
        if (totalEquipos >= 10) {
            JOptionPane.showMessageDialog(null, "Máximo de equipos alcanzado (10)");
            return;
        }

        String nombre = Data.pedirTexto("Nombre del equipo:");
        int performance = Data.pedirEntero("Performance (0-10):", 0, 10);
        double presupuesto = Data.pedirDecimal("Presupuesto:");

        equipos[totalEquipos++] = new Equipo(nombre, performance, presupuesto);
        JOptionPane.showMessageDialog(null, "Equipo registrado con ID: " + equipos[totalEquipos-1].getId());
    }

    public void registrarCorredor() {
        if (totalEquipos == 0) {
            JOptionPane.showMessageDialog(null, "Primero registre al menos un equipo");
            return;
        }

        Equipo equipo = seleccionarEquipo("Seleccione equipo:", "Registrar Corredor");
        if (equipo == null) return;

        if (equipo.tieneMaxCorredores()) {
            JOptionPane.showMessageDialog(null, "Este equipo ya tiene 2 corredores");
            return;
        }

        String nombre = Data.pedirTexto("Nombre del corredor:");
        int numero = Data.pedirEntero("Número único del corredor (1-999):", 1, 999);
        while (numeroExiste(numero)) {
            JOptionPane.showMessageDialog(null, "Número ya existe");
            numero = Data.pedirEntero("Número único del corredor (1-999):", 1, 999);
        }

        int habilidad = Data.pedirEntero("Habilidad (0-10):", 0, 10);
        int experiencia = Data.pedirEntero("Experiencia (0-10):", 0, 10);

        Corredor nuevo = new Corredor(nombre, numero, habilidad, experiencia, equipo.getId());
        equipo.agregarCorredor(nuevo);
        JOptionPane.showMessageDialog(null, "Corredor registrado con ID: " + nuevo.getId());
    }

    public void asignarPrincipal() {
        for (Equipo e : equipos) {
            if (e == null) continue;
            
            if (!e.tieneCorredores()) {
                JOptionPane.showMessageDialog(null, e.getNombre() + " no tiene corredores");
                continue;
            }

            Corredor seleccionado = seleccionarCorredor(e, "Seleccione corredor principal para " + e.getNombre() + ":");
            if (seleccionado != null) {
                e.setPrincipal(seleccionado);
            }
        }
    }

    public void mostrarEquiposCorredores() {
        StringBuilder sb = new StringBuilder("=== EQUIPOS Y CORREDORES ===\n\n");
        for (Equipo e : equipos) {
            if (e == null) continue;
            
            sb.append(e.getId()).append(" | ").append(e.getNombre())
              .append(" | Performance: ").append(e.getPerformance())
              .append(" | Presupuesto: $").append(e.getPresupuesto()).append("\n");
            
            for (Corredor c : e.getCorredores()) {
                if (c != null) {
                    sb.append("   ").append(c.getId()).append(" | ").append(c.getNombre())
                      .append(" (#").append(c.getNumero()).append(") | Habilidad: ").append(c.getHabilidad())
                      .append(" | Experiencia: ").append(c.getExperiencia());
                    if (e.getPrincipal() == c) sb.append(" [PRINCIPAL]");
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    public int getTotalEquipos() {
        return totalEquipos;
    }

    private boolean numeroExiste(int numero) {
        for (Equipo e : equipos) {
            if (e != null && e.existeNumeroCorredor(numero)) {
                return true;
            }
        }
        return false;
    }

    private Equipo seleccionarEquipo(String mensaje, String titulo) {
        String[] opciones = new String[totalEquipos];
        for (int i = 0; i < totalEquipos; i++) {
            opciones[i] = equipos[i].getId() + " - " + equipos[i].getNombre();
        }

        String seleccion = (String) JOptionPane.showInputDialog(null, 
            mensaje, titulo, 
            JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == null) return null;

        for (Equipo e : equipos) {
            if (e != null && seleccion.startsWith(e.getId())) {
                return e;
            }
        }
        return null;
    }

    private Corredor seleccionarCorredor(Equipo equipo, String mensaje) {
        String[] opciones = new String[2];
        int count = 0;
        Corredor[] corredores = equipo.getCorredores();
        
        if (corredores[0] != null) opciones[count++] = corredores[0].getNombre() + " (" + corredores[0].getNumero() + ")";
        if (corredores[1] != null) opciones[count++] = corredores[1].getNombre() + " (" + corredores[1].getNumero() + ")";

        String seleccion = (String) JOptionPane.showInputDialog(null,
            mensaje, "Asignar Principal", JOptionPane.QUESTION_MESSAGE, null,
            opciones, opciones[0]);

        if (seleccion != null) {
            if (seleccion.equals(opciones[0])) {
                return corredores[0];
            } else if (count > 1 && seleccion.equals(opciones[1])) {
                return corredores[1];
            }
        }
        return null;
    }
}