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

public class Carreras {
    private final Carrera[] historialCarreras = new Carrera[50];
    private int totalCarreras = 0;
    private static final double DISTANCIA_VUELTA = 15.0; // 15 km por vuelta

    public void simularCarrera(Equipos gestorEquipos) {
        Equipo[] equipos = gestorEquipos.getEquipos();
        
        if (gestorEquipos.getTotalEquipos() < 2) {
            JOptionPane.showMessageDialog(null, "Se necesitan al menos 2 equipos");
            return;
        }

        // Verificar que todos los equipos tengan corredor principal
        for (Equipo e : equipos) {
            if (e != null && e.getPrincipal() == null) {
                JOptionPane.showMessageDialog(null, e.getNombre() + " no tiene corredor principal asignado");
                return;
            }
        }

        String nombreCarrera = Data.pedirTexto("Nombre de la carrera:");
        int vueltas = Data.pedirEntero("Número de vueltas (1-100):", 1, 100);

        // Simular carrera
        Equipo ganador = null;
        Corredor corredorGanador = null;
        double mejorTiempo = Double.MAX_VALUE;

        for (Equipo e : equipos) {
            if (e == null || e.getPrincipal() == null) continue;

            Corredor c = e.getPrincipal();
            double coef = calcularCoeficiente(c, e);
            
            double velocidad = 200 * coef; // km/h
            double tiempoVuelta = (DISTANCIA_VUELTA / velocidad) * 60; // en minutos
            double tiempoTotal = tiempoVuelta * vueltas;

            if (tiempoTotal < mejorTiempo || 
                (tiempoTotal == mejorTiempo && c.getExperiencia() > corredorGanador.getExperiencia())) {
                mejorTiempo = tiempoTotal;
                ganador = e;
                corredorGanador = c;
            }
        }

        // Guardar carrera en historial
        historialCarreras[totalCarreras++] = new Carrera(
            nombreCarrera, vueltas, DISTANCIA_VUELTA * vueltas,
            ganador.getNombre(), corredorGanador.getNombre(), mejorTiempo
        );

        mostrarPodio(nombreCarrera, vueltas, ganador, corredorGanador);
    }

    private double calcularCoeficiente(Corredor c, Equipo e) {
        double coef = (c.getHabilidad() * 0.20) + (c.getExperiencia() * 0.25) + (e.getPerformance() * 0.55);
        
        // Reglas especiales
        if (c.getHabilidad() == 10) coef *= 1.05;
        if (c.getExperiencia() < 3) coef *= 0.90;
        
        return coef;
    }

    private void mostrarPodio(String nombreCarrera, int vueltas, Equipo ganador, Corredor corredorGanador) {
        StringBuilder podio = new StringBuilder("===== PODIO FINAL =====\n");
        podio.append(nombreCarrera).append(" - ").append(vueltas).append(" vueltas\n\n");
        podio.append("1º ").append(ganador.getNombre()).append(" - ").append(corredorGanador.getNombre()).append("\n");
        podio.append("\n¡Gran Premio Finalizado!");
        
        JOptionPane.showMessageDialog(null, podio.toString());
    }

    public Carrera[] getHistorialCarreras() {
        return historialCarreras;
    }

    public int getTotalCarreras() {
        return totalCarreras;
    }
}