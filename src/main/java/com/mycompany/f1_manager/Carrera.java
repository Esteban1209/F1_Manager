/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */

public class Carrera {
    private final String nombre;
    private final int vueltas;
    private final double distanciaTotal;
    private final String equipoGanador;
    private final String corredorGanador;
    private final double tiempoGanador;

    public Carrera(String nombre, int vueltas, double distanciaTotal, 
                  String equipoGanador, String corredorGanador, double tiempoGanador) {
        this.nombre = nombre;
        this.vueltas = vueltas;
        this.distanciaTotal = distanciaTotal;
        this.equipoGanador = equipoGanador;
        this.corredorGanador = corredorGanador;
        this.tiempoGanador = tiempoGanador;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getVueltas() { return vueltas; }
    public double getDistanciaTotal() { return distanciaTotal; }
    public String getEquipoGanador() { return equipoGanador; }
    public String getCorredorGanador() { return corredorGanador; }
    public double getTiempoGanador() { return tiempoGanador; }
}