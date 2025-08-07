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
 * Clase Equipo - Representa un equipo de Fórmula 1 con sus propiedades y corredores.
 * Cada equipo tiene un identificador único, nombre, performance, presupuesto y hasta 2 corredores.
 */
public class Equipo {
    // ==================== ATRIBUTOS ====================
    
    private final String id;           // Identificador único del equipo (ej: "EQP-00001")
    private final String nombre;      // Nombre del equipo (ej: "Mercedes AMG")
    private final int performance;    // Nivel de performance del equipo (0-10)
    private final double presupuesto; // Presupuesto del equipo en millones
    private final Corredor[] corredores = new Corredor[2]; // Array para 2 corredores máximo
    private Corredor principal;       // Corredor principal del equipo
    private static int contador = 1;  // Contador estático para generar IDs únicos

    // ==================== CONSTRUCTOR ====================
    
    /**
     * Constructor para crear un nuevo equipo
     * @param nombre Nombre del equipo (no puede ser vacío)
     * @param performance Nivel de performance (0-10)
     * @param presupuesto Presupuesto en millones
     */
    public Equipo(String nombre, int performance, double presupuesto) {
        this.id = "EQP-" + String.format("%05d", contador++); // Genera ID autoincremental
        this.nombre = nombre;
        this.performance = performance;
        this.presupuesto = presupuesto;
    }

    // ==================== MÉTODOS DE GESTIÓN DE CORREDORES ====================
    
    /**
     * Agrega un corredor al equipo si hay espacio disponible
     * @param corredor Objeto Corredor a agregar
     * @return true si se agregó correctamente, false si no hay espacio
     */
    public boolean agregarCorredor(Corredor corredor) {
        if (corredores[0] == null) {
            corredores[0] = corredor;
            return true;
        } else if (corredores[1] == null) {
            corredores[1] = corredor;
            return true;
        }
        return false; // Retorna false si ya hay 2 corredores
    }

    /**
     * Verifica si el equipo tiene al menos un corredor
     * @return true si tiene corredores, false si está vacío
     */
    public boolean tieneCorredores() {
        return corredores[0] != null || corredores[1] != null;
    }

    /**
     * Verifica si el equipo ya tiene 2 corredores (máximo permitido)
     * @return true si tiene 2 corredores, false si tiene 0 o 1
     */
    public boolean tieneMaxCorredores() {
        return corredores[0] != null && corredores[1] != null;
    }

    /**
     * Busca si algún corredor del equipo tiene un número específico
     * @param numero Número a buscar
     * @return true si el número ya está en uso, false si está disponible
     */
    public boolean existeNumeroCorredor(int numero) {
        for (Corredor c : corredores) {
            if (c != null && c.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }
    /**
     * Busca si algún corredor del equipo tiene un nombre específico
     * @param nombre Nombre a buscar (ignora mayúsculas/minúsculas y espacios)
     * @return true si el nombre existe, false si no
     */
    public boolean contieneCorredorConNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        String nombreBuscado = nombre.trim().toLowerCase();
        for (Corredor c : corredores) {
            if (c != null && c.getNombre().trim().toLowerCase().equals(nombreBuscado)) {
                return true;
            }
        }
        return false;
    }

    // ==================== GETTERS & SETTERS ====================
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPerformance() { return performance; }
    public double getPresupuesto() { return presupuesto; }
    public Corredor[] getCorredores() { return corredores; }
    public Corredor getPrincipal() { return principal; }
    
    public void setPrincipal(Corredor principal) { this.principal = principal; } //Setter
}