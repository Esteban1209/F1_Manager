/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */

/**
 * Clase Carrera - Representa una carrera de Fórmula 1 con sus resultados.
 * Almacena información sobre la carrera y el equipo/corredor ganador.
 * Todos los atributos son inmutables (final) una vez creada la carrera.
 */
public class Carrera {
    
    // ==================== ATRIBUTOS ====================
    
    private final String nombre;          // Nombre del circuito o gran premio (ej: "Gran Premio de Mónaco")
    private final int vueltas;           // Número total de vueltas de la carrera
    private final double distanciaTotal;  // Distancia total en kilómetros
    private final String equipoGanador;   // ID del equipo ganador (ej: "EQP-00001")
    private final String corredorGanador; // ID del corredor ganador (ej: "DR-00005")
    private final double tiempoGanador;   // Tiempo total del ganador en segundos

    // ==================== CONSTRUCTOR ====================
    
    /**
     * Constructor para crear una nueva carrera con resultados
     * @param nombre Nombre de la carrera
     * @param vueltas Número de vueltas completadas
     * @param distanciaTotal Distancia total en kilómetros
     * @param equipoGanador ID del equipo ganador
     * @param corredorGanador ID del corredor ganador
     * @param tiempoGanador Tiempo total del ganador en segundos
     */
    public Carrera(String nombre, int vueltas, double distanciaTotal, 
                  String equipoGanador, String corredorGanador, double tiempoGanador) {
        this.nombre = nombre;
        this.vueltas = vueltas;
        this.distanciaTotal = distanciaTotal;
        this.equipoGanador = equipoGanador;
        this.corredorGanador = corredorGanador;
        this.tiempoGanador = tiempoGanador;
    }

    // ==================== GETTERS ====================
    
    /**
     * @return Nombre de la carrera
     */
    public String getNombre() { return nombre; }

    /**
     * @return Número de vueltas completadas
     */
    public int getVueltas() { return vueltas; }

    /**
     * @return Distancia total en kilómetros
     */
    public double getDistanciaTotal() { return distanciaTotal; }

    /**
     * @return ID del equipo ganador
     */
    public String getEquipoGanador() { return equipoGanador; }

    /**
     * @return ID del corredor ganador
     */
    public String getCorredorGanador() { return corredorGanador; }

    /**
     * @return Tiempo total del ganador en segundos
     */
    public double getTiempoGanador() { return tiempoGanador; }
}