/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */
public class Corredor {
    private final String id;
    private final String nombre;
    private final int numero;
    private final int habilidad;
    private final int experiencia;
    private final String equipoId;
    private static int contador = 1;

    public Corredor(String nombre, int numero, int habilidad, int experiencia, String equipoId) {
        this.id = "DR-" + String.format("%05d", contador++);
        this.nombre = nombre;
        this.numero = numero;
        this.habilidad = habilidad;
        this.experiencia = experiencia;
        this.equipoId = equipoId;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getNumero() { return numero; }
    public int getHabilidad() { return habilidad; }
    public int getExperiencia() { return experiencia; }
    public String getEquipoId() { return equipoId; }
}