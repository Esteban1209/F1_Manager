/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;
import java.io.FileWriter;  // Para escribir en archivos
import java.io.IOException; // Para manejar errores de escritura
import javax.swing.JOptionPane; 
/**
 *
 * @author hp
 */
/**
 * Clase GestorReportes - Se encarga de generar reportes en formato CSV
 * con información de equipos, corredores y carreras de Fórmula 1.
 */
public class GestorReportes {

    /**
     * Método principal que genera todos los reportes
     * @param gestorEquipos Objeto que contiene la información de los equipos
     * @param gestorCarreras Objeto que contiene la información de las carreras
     */
    public void generarReportes(Equipos gestorEquipos, Carreras gestorCarreras) {
        // Generamos los tres reportes:
        generarReporteEquipos(gestorEquipos);     // Reporte de equipos
        generarReporteCorredores(gestorEquipos);  // Reporte de corredores
        generarReporteCarreras(gestorCarreras);   // Reporte de carreras
        
        // Mostramos mensaje de éxito:
        JOptionPane.showMessageDialog(null, 
            "Reportes generados exitosamente:\n" +
            "- equipos.csv\n" + 
            "- corredores.csv\n" +
            "- carreras.csv");
    }

    /**
     * Genera un archivo CSV con la información de todos los equipos
     * @param gestorEquipos Objeto que contiene los datos de los equipos
     */
    private void generarReporteEquipos(Equipos gestorEquipos) {
        try (FileWriter fw = new FileWriter("equipos.csv")) {
            // Escribimos la cabecera del archivo CSV
            fw.write("ID,Nombre,Performance,Presupuesto\n");
            
            // Recorremos todos los equipos
            for (Equipo e : gestorEquipos.getEquipos()) {
                if (e != null) {  // Verificamos que el equipo exista
                    // Escribimos los datos del equipo en formato CSV
                    fw.write(String.format("%s,%s,%d,%.2f%n",
                        e.getId(),          // ID del equipo
                        e.getNombre(),      // Nombre del equipo
                        e.getPerformance(), // Rendimiento del equipo
                        e.getPresupuesto())); // Presupuesto del equipo
                }
            }
        } catch (IOException e) {
            // Si hay error, mostramos mensaje
            JOptionPane.showMessageDialog(null, 
                "Error al guardar equipos.csv\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Genera un archivo CSV con la información de todos los corredores
     * @param gestorEquipos Objeto que contiene los datos de los equipos (y sus corredores)
     */
    private void generarReporteCorredores(Equipos gestorEquipos) {
        try (FileWriter fw = new FileWriter("corredores.csv")) {
            // Cabecera del CSV
            fw.write("ID,Nombre,Numero,Habilidad,Experiencia,Equipo\n");
            
            // Recorremos todos los equipos
            for (Equipo e : gestorEquipos.getEquipos()) {
                if (e != null) {  // Verificamos que el equipo exista
                    // Recorremos los corredores del equipo
                    for (Corredor c : e.getCorredores()) {
                        if (c != null) {  // Verificamos que el corredor exista
                            // Escribimos los datos del corredor
                            fw.write(String.format("%s,%s,%d,%d,%d,%s%n",
                                c.getId(),           // ID del corredor
                                c.getNombre(),       // Nombre del corredor
                                c.getNumero(),       // Número del corredor
                                c.getHabilidad(),    // Nivel de habilidad
                                c.getExperiencia(),  // Años de experiencia
                                e.getId()));        // ID del equipo al que pertenece
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(null,
                "Error al guardar corredores.csv\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Genera un archivo CSV con el historial de carreras
     * @param gestorCarreras Objeto que contiene los datos de las carreras
     */
    private void generarReporteCarreras(Carreras gestorCarreras) {
        try (FileWriter fw = new FileWriter("carreras.csv")) {
            // Cabecera del CSV
            fw.write("Carrera,Vueltas,Distancia,Equipo Ganador,Corredor Ganador,Tiempo Total\n");
            
            // Obtenemos el historial de carreras de forma segura
            Carrera[] carreras = gestorCarreras.getHistorialCarreras();
            int total = gestorCarreras.getTotalCarreras();  // Número real de carreras
            
            // Recorremos solo las carreras existentes (evitamos nulos)
            for (int i = 0; i < total; i++) {
                if (carreras[i] != null) {
                    Carrera c = carreras[i];
                    // Escribimos los datos de la carrera
                    fw.write(String.format("%s,%d,%.2f,%s,%s,%.2f%n",
                        c.getNombre(),          // Nombre de la carrera
                        c.getVueltas(),          // Número de vueltas
                        c.getDistanciaTotal(),   // Distancia total
                        c.getEquipoGanador(),   // Equipo ganador
                        c.getCorredorGanador(),  // Corredor ganador
                        c.getTiempoGanador()));  // Tiempo del ganador
                }
            }
        } catch (IOException e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(null,
                "Error al guardar carreras.csv\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}