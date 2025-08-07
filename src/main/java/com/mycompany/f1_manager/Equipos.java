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

/**
 * Clase Equipos - Gestiona los equipos y corredores de Fórmula 1
 * Permite registrar, asignar y mostrar información de equipos y sus pilotos
 */
public class Equipos {
    // Array para almacenar hasta 10 equipos
    private final Equipo[] equipos = new Equipo[10];
    // Contador de equipos registrados actualmente
    private int totalEquipos = 0;

    /**
     * Registra un nuevo equipo en el sistema
     * Valida que no se exceda el límite y que el nombre sea único
     */
    public void registrarEquipo() {
        // Verificar si ya se alcanzó el máximo de equipos
        if (totalEquipos >= 10) {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más equipos. Límite alcanzado (10).");
            return;
        }

        String nombre;
        boolean nombreRepetido;
        do {
            // Pedir nombre del equipo
            nombre = JOptionPane.showInputDialog("Nombre del equipo:");

            // Validar si el usuario canceló
            if (nombre == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                return;
            } 
            // Validar nombre vacío
            else if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío. Intente nuevamente.");
                nombreRepetido = true;
                continue;
            }

            // Verificar si el nombre ya existe
            nombreRepetido = existeNombreEquipo(nombre);
            if (nombreRepetido) {
                JOptionPane.showMessageDialog(null, "El nombre '" + nombre + "' ya está en uso. Elija otro.");
            }
        } while (nombreRepetido || nombre.trim().isEmpty());

        // Pedir performance (validada entre 0-10)
        int performance = Data.pedirEntero("Performance (0-10):", 0, 10);
        // Pedir presupuesto
        double presupuesto = Double.parseDouble(JOptionPane.showInputDialog("Presupuesto:"));
            
        // Crear y agregar el nuevo equipo
        equipos[totalEquipos++] = new Equipo(nombre, performance, presupuesto);
        // Mostrar confirmación con ID generado
        JOptionPane.showMessageDialog(null, "Equipo registrado con ID: " + equipos[totalEquipos-1].getId());
    }
    
    /**
     * Registra un nuevo corredor en un equipo existente
     * Valida que existan equipos, que no estén llenos y que los datos sean válidos
     */
    public void registrarCorredor() {
        // Verificar que haya equipos registrados
        if (totalEquipos == 0) {
            JOptionPane.showMessageDialog(null, "Primero registre al menos un equipo");
            return;
        }

        // Seleccionar equipo para el corredor
        Equipo equipo = seleccionarEquipo("Seleccione equipo:", "Registrar Corredor");
        if (equipo == null) return; // Si el usuario canceló

        // Verificar que el equipo no tenga ya 2 corredores
        if (equipo.tieneMaxCorredores()) {
            JOptionPane.showMessageDialog(null, "Este equipo ya tiene 2 corredores");
            return;
        }

        String nombre;
        boolean nombreRepetido;
        do {
            // Pedir nombre del corredor
            nombre = JOptionPane.showInputDialog("Nombre del corredor:");
            if (nombre == null) return; // Si el usuario cancela
        
            // Verificar si el nombre ya existe en cualquier equipo
            nombreRepetido = existeNombreCorredorEnCualquierEquipo(nombre);
        
            if (nombreRepetido) {
                JOptionPane.showMessageDialog(null, 
                    "¡Error! El corredor '" + nombre + "' ya existe en otro equipo");
            }
        } while (nombreRepetido || nombre.trim().isEmpty());
        
        // Pedir número único del corredor (validado)
        int numero = Data.pedirEntero("Número único del corredor (1-999):", 1, 999);
        while (numeroExiste(numero)) {
            JOptionPane.showMessageDialog(null, "Número ya existe");
            numero = Data.pedirEntero("Número único del corredor (1-999):", 1, 999);
        }

        // Pedir habilidades (validados)
        int habilidad = Data.pedirEntero("Habilidad (0-10):", 0, 10);
        int experiencia = Data.pedirEntero("Experiencia (0-10):", 0, 10);

        // Crear y agregar el corredor al equipo
        Corredor nuevo = new Corredor(nombre, numero, habilidad, experiencia, equipo.getId());
        equipo.agregarCorredor(nuevo);
        // Mostrar confirmación con ID generado
        JOptionPane.showMessageDialog(null, "Corredor registrado con ID: " + nuevo.getId());
    }

    /**
     * Verifica si un nombre de corredor ya existe en cualquier equipo
     */
    private boolean existeNombreCorredorEnCualquierEquipo(String nombre) {
        if (nombre == null) return false;
    
        // Buscar en todos los equipos
        for (int i = 0; i < totalEquipos; i++) {
            if (equipos[i] != null && equipos[i].contieneCorredorConNombre(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Asigna un corredor principal para cada equipo
     */
    public void asignarPrincipal() {
        // Recorrer todos los equipos
        for (Equipo e : equipos) {
            if (e == null) continue; // Saltar equipos nulos
            
            // Verificar si el equipo tiene corredores
            if (!e.tieneCorredores()) {
                JOptionPane.showMessageDialog(null, e.getNombre() + " no tiene corredores");
                continue;
            }

            // Seleccionar corredor principal
            Corredor seleccionado = seleccionarCorredor(e, "Seleccione corredor principal para " + e.getNombre() + ":");
            if (seleccionado != null) {
                e.setPrincipal(seleccionado);
            }
        }
    }

    /**
     * Muestra todos los equipos y sus corredores en un cuadro de diálogo
     */
    public void mostrarEquiposCorredores() {
        // Usar StringBuilder para construir el mensaje eficientemente
        StringBuilder sb = new StringBuilder("=== EQUIPOS Y CORREDORES ===\n\n");
        
        // Recorrer todos los equipos
        for (Equipo e : equipos) {
            if (e == null) continue; // Saltar equipos nulos
            
            // Agregar información del equipo
            sb.append(e.getId()).append(" | ").append(e.getNombre())
              .append(" | Performance: ").append(e.getPerformance())
              .append(" | Presupuesto: $").append(e.getPresupuesto()).append("\n");
            
            // Agregar información de cada corredor del equipo
            for (Corredor c : e.getCorredores()) {
                if (c != null) {
                    sb.append("   ").append(c.getId()).append(" | ").append(c.getNombre())
                      .append(" (#").append(c.getNumero()).append(") | Habilidad: ").append(c.getHabilidad())
                      .append(" | Experiencia: ").append(c.getExperiencia());
                    // Marcar si es el corredor principal
                    if (e.getPrincipal() == c) sb.append(" [PRINCIPAL]");
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }
        // Mostrar toda la información en un cuadro de diálogo
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // ==================== MÉTODOS AUXILIARES ====================

    /**
     * Verifica si un número ya está en uso por algún corredor
     */
    private boolean numeroExiste(int numero) {
        for (Equipo e : equipos) {
            if (e != null && e.existeNumeroCorredor(numero)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un nombre de equipo ya existe
     */
    private boolean existeNombreEquipo(String nombre) {
        if (nombre == null) return false;
    
        for (int i = 0; i < totalEquipos; i++) {
            if (equipos[i] != null && 
                equipos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permite seleccionar un equipo de una lista desplegable
     */
    private Equipo seleccionarEquipo(String mensaje, String titulo) {
        // Crear array de opciones para el diálogo
        String[] opciones = new String[totalEquipos];
        for (int i = 0; i < totalEquipos; i++) {
            opciones[i] = equipos[i].getId() + " - " + equipos[i].getNombre();
        }

        // Mostrar diálogo de selección
        String seleccion = (String) JOptionPane.showInputDialog(null, 
            mensaje, titulo, 
            JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Si el usuario canceló
        if (seleccion == null) return null;

        // Buscar el equipo seleccionado
        for (Equipo e : equipos) {
            if (e != null && seleccion.startsWith(e.getId())) {
                return e;
            }
        }
        return null;
    }

    /**
     * Permite seleccionar un corredor de un equipo específico
     */
    private Corredor seleccionarCorredor(Equipo equipo, String mensaje) {
        String[] opciones = new String[2];
        int count = 0;
        Corredor[] corredores = equipo.getCorredores();
        
        // Construir opciones para los corredores existentes
        if (corredores[0] != null) opciones[count++] = corredores[0].getNombre() + " (" + corredores[0].getNumero() + ")";
        if (corredores[1] != null) opciones[count++] = corredores[1].getNombre() + " (" + corredores[1].getNumero() + ")";

        // Mostrar diálogo de selección
        String seleccion = (String) JOptionPane.showInputDialog(null,
            mensaje, "Asignar Principal", JOptionPane.QUESTION_MESSAGE, null,
            opciones, opciones[0]);

        // Retornar el corredor seleccionado
        if (seleccion != null) {
            if (seleccion.equals(opciones[0])) {
                return corredores[0];
            } else if (count > 1 && seleccion.equals(opciones[1])) {
                return corredores[1];
            }
        }
        return null;
    }
    // ==================== GETTERS ====================

    public Equipo[] getEquipos() {
        return equipos;
    }

    public int getTotalEquipos() {
        return totalEquipos;
    }
}