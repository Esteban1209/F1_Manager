/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */

public class Equipo {
    private final String id;
    private final String nombre;
    private final int performance;
    private final double presupuesto;
    private final Corredor[] corredores = new Corredor[2];
    private Corredor principal;
    private static int contador = 1;

    public Equipo(String nombre, int performance, double presupuesto) {
        this.id = "EQP-" + String.format("%05d", contador++);
        this.nombre = nombre;
        this.performance = performance;
        this.presupuesto = presupuesto;
    }

    public boolean agregarCorredor(Corredor corredor) {
        if (corredores[0] == null) {
            corredores[0] = corredor;
            return true;
        } else if (corredores[1] == null) {
            corredores[1] = corredor;
            return true;
        }
        return false;
    }

    public boolean tieneCorredores() {
        return corredores[0] != null || corredores[1] != null;
    }

    public boolean tieneMaxCorredores() {
        return corredores[0] != null && corredores[1] != null;
    }

    public boolean existeNumeroCorredor(int numero) {
        for (Corredor c : corredores) {
            if (c != null && c.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPerformance() { return performance; }
    public double getPresupuesto() { return presupuesto; }
    public Corredor[] getCorredores() { return corredores; }
    public Corredor getPrincipal() { return principal; }
    
    // Setter
    public void setPrincipal(Corredor principal) { this.principal = principal; }
}