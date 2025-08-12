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
/**
 * CLASE QUE GESTIONA TODAS LAS CARRERAS DE FÓRMULA 1
 * 
 * Funcionalidades principales:
 * - Simulación de carreras con física básica
 * - Historial de resultados
 * - Visualización de podios
 */
public class Carreras {
    // Array para almacenar hasta 50 carreras
    private Carrera[] historialCarreras = new Carrera[50];
    
    // Contador de carreras realizadas
    private int totalCarreras = 0;
    
    // Longitud fija de la pista (15 km por vuelta)
    private static final double DISTANCIA_VUELTA = 15.0;

    // MÉTODOS PÚBLICOS

    /**
     * Simula una nueva carrera con los equipos disponibles
     * @param gestorEquipos Objeto que contiene los equipos registrados
     */
    public void simularCarrera(Equipos gestorEquipos) {
        // Validación básica: mínimo 2 equipos
        if (!validarEquipos(gestorEquipos)) {
            return;
        }

        // Configuración de la carrera
        String nombreCarrera = pedirNombreCarrera();
        if (nombreCarrera == null) return; // Si el usuario cancela

        int vueltas = Data.pedirEntero("Ingrese número de vueltas (1-100):", 1, 100);

        // Simulación y resultados
        ResultadoCarrera[] resultados = calcularResultados(gestorEquipos, vueltas);
        ordenarResultados(resultados);

        // Guardar y mostrar resultados
        guardarCarreraEnHistorial(nombreCarrera, vueltas, resultados);
        mostrarPodio(nombreCarrera, vueltas, resultados);
    }

    /**
     * Obtiene el historial completo de carreras
     * @return Array de objetos Carrera
     */
    public Carrera[] getHistorialCarreras() {
        return this.historialCarreras;
    }

    /**
     * Obtiene el número total de carreras realizadas
     * @return Número de carreras
     */
    public int getTotalCarreras() {
        return this.totalCarreras;
    }

    // MÉTODOS PRIVADOS

    /**
     * Valida los requisitos mínimos para una carrera
     */
    private boolean validarEquipos(Equipos gestorEquipos) {
        // Verificar cantidad mínima de equipos
        if (gestorEquipos.getTotalEquipos() < 2) {
            JOptionPane.showMessageDialog(null, 
                "¡Se necesitan al menos 2 equipos para correr!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Verificar corredores principales
        for (Equipo equipo : gestorEquipos.getEquipos()) {
            if (equipo != null && equipo.getPrincipal() == null) {
                JOptionPane.showMessageDialog(null, 
                    "El equipo " + equipo.getNombre() + " no tiene piloto principal", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    /**
     * Solicita el nombre de la carrera al usuario
     */
    private String pedirNombreCarrera() {
        String nombre;
        do {
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del Gran Premio:");
            if (nombre == null) return null; // Cancelado por usuario
            
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "El nombre no puede estar vacío", 
                    "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (nombre.trim().isEmpty());
        
        return nombre;
    }

    /**
     * Calcula los resultados para todos los corredores
     */
    private ResultadoCarrera[] calcularResultados(Equipos gestorEquipos, int vueltas) {
        ResultadoCarrera[] resultados = new ResultadoCarrera[gestorEquipos.getTotalEquipos()];
        int index = 0;
        
        for (Equipo equipo : gestorEquipos.getEquipos()) {
            if (equipo == null) continue;
            
            Corredor piloto = equipo.getPrincipal();
            double coeficiente = calcularCoeficiente(piloto, equipo);
            double velocidad = 200 * coeficiente;
            double tiempoTotal = (DISTANCIA_VUELTA / velocidad) * 3600 * vueltas; //Para sacar segundos, si necesitamos sacar el resultados en minutos cambiamos a 60 en vez de 3600
            
            resultados[index++] = new ResultadoCarrera(
                equipo.getNombre(),
                piloto.getNombre(),
                tiempoTotal,
                piloto.getExperiencia()
            );
        }
        return resultados;
    }

    /**
     * Fórmula para calcular el coeficiente de rendimiento
     */
    private double calcularCoeficiente(Corredor piloto, Equipo equipo) {
        double coef = (piloto.getHabilidad() * 0.20) + 
                     (piloto.getExperiencia() * 0.25) + 
                     (equipo.getPerformance() * 0.55);
        
        // Bonificaciones/penalizaciones
        if (piloto.getHabilidad() == 10) coef *= 1.05;
        if (piloto.getExperiencia() < 3) coef *= 0.90;
        
        return coef;
    }

    /**
     * Ordena los resultados por tiempo (y experiencia en empates)
     */
    private void ordenarResultados(ResultadoCarrera[] resultados) {
        for (int i = 0; i < resultados.length-1; i++) {
            if (resultados[i] == null) continue;
            
            for (int j = 0; j < resultados.length-i-1; j++) {
                if (resultados[j] == null || resultados[j+1] == null) continue;
                
                boolean debeIntercambiar = 
                    resultados[j].getTiempo() > resultados[j+1].getTiempo() ||
                    (resultados[j].getTiempo() == resultados[j+1].getTiempo() && 
                     resultados[j].getExperiencia() < resultados[j+1].getExperiencia());
                
                if (debeIntercambiar) {
                    ResultadoCarrera temp = resultados[j];
                    resultados[j] = resultados[j+1];
                    resultados[j+1] = temp;
                }
            }
        }
    }

    /**
     * Guarda los resultados en el historial
     */
    private void guardarCarreraEnHistorial(String nombre, int vueltas, ResultadoCarrera[] resultados) {
        if (resultados.length > 0 && resultados[0] != null) {
            historialCarreras[totalCarreras++] = new Carrera(
                nombre,
                vueltas,
                DISTANCIA_VUELTA * vueltas,
                resultados[0].getEquipo(),
                resultados[0].getCorredor(),
                resultados[0].getTiempo()
            );
        }
    }
    /**
     * Muestra el podio en una ventana emergente
     */
    private void mostrarPodio(String nombre, int vueltas, ResultadoCarrera[] resultados) {
        StringBuilder sb = new StringBuilder();
        sb.append("===== PODIO FINAL =====\n")
          .append(nombre).append(" - ").append(vueltas).append(" Vueltas\n");
        
        // Mostrar solo los 3 primeros puestos
        for (int i = 0; i < 3 && i < resultados.length; i++) {
            if (resultados[i] != null) {
                sb.append(i+1).append("º ")  // Número ordinal (1º, 2º, 3º)
                  .append(resultados[i].getEquipo()).append(" - ")
                  .append(resultados[i].getCorredor()).append("\n");
            }
        }
        
        sb.append("\n¡Gran Premio Finalizado!");
        
        JOptionPane.showMessageDialog(
            null, 
            sb.toString(), 
            "Resultados de Carrera", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // CLASE INTERNA PARA RESULTADOS TEMPORALES
    private static class ResultadoCarrera {
        private final String equipo;
        private final String corredor;
        private final double tiempo;
        private final int experiencia;

        public ResultadoCarrera(String equipo, String corredor, double tiempo, int experiencia) {
            this.equipo = equipo;
            this.corredor = corredor;
            this.tiempo = tiempo;
            this.experiencia = experiencia;
        }

        public String getEquipo() { return equipo; }
        public String getCorredor() { return corredor; }
        public double getTiempo() { return tiempo; }
        public int getExperiencia() { return experiencia; }
    }
    
}