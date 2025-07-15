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
public class Equipo {
    String id;
    String nombre;
    int performance;
    double presupuesto;
    Corredor[] corredores = new Corredor[2];
    Corredor principal;
    static int contador = 1;

    public Equipo(String nombre, int performance, double presupuesto) {
        this.nombre = nombre;
        this.performance = performance;
        this.presupuesto = presupuesto;
        this.id = String.format("EQP-%05d", contador++);
    }

    public boolean agregarCorredor(Corredor c) {
        if (corredores[0] == null) {
            corredores[0] = c;
            return true;
        } else if (corredores[1] == null) {
            corredores[1] = c;
            return true;
        } else {
            return false;
        }
    }

    public boolean tieneEspacio() {
        return corredores[0] == null || corredores[1] == null;
    }

    public Corredor[] getCorredores() {
        return corredores;
    }
}