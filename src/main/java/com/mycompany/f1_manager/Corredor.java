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
    String id;
    String nombre;
    int numero;
    int habilidad;
    int experiencia;
    String equipoID;
    static int contador = 1;

    public Corredor(String nombre, int numero, int habilidad, int experiencia, String equipoID) {
        this.nombre = nombre;
        this.numero = numero;
        this.habilidad = habilidad;
        this.experiencia = experiencia;
        this.equipoID = equipoID;
        this.id = String.format("DR-%05d", contador++);
    }
}