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
    private final String id;          // Identificador único del corredor (ej: "DR-00001")
    private final String nombre;      // Nombre completo del piloto (ej: "Lewis Hamilton")
    private final int numero;         // Número (ej: 5)
    private final int habilidad;      // Nivel de habilidad (ej: 0/10)
    private final int experiencia;    // Nivel de experiencia (ej: 10)
    private final String equipoId;    // ID del equipo al que pertenece (ej: "EQ-001")
    private static int contador = 1;  // Contador para generar IDs automáticos

    public Corredor(String nombre, int numero, int habilidad, int experiencia, String equipoId) {
        this.id = "DR-" + String.format("%05d", contador++);  // Genera ID automático (ej: DR-00001)
        this.nombre = nombre;      // Asigna el nombre
        this.numero = numero;      // Asigna el número
        this.habilidad = habilidad; // Asigna la habilidad
        this.experiencia = experiencia; // Asigna la experiencia
        this.equipoId = equipoId;  // Asigna el ID del equipo
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getNumero() { return numero; }
    public int getHabilidad() { return habilidad; }
    public int getExperiencia() { return experiencia; }
    public String getEquipoId() { return equipoId; }
}