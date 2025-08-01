/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author hp
 */

public class GestorReportes {

    public void generarReportes(Equipos gestorEquipos, Carreras gestorCarreras) {
        generarReporteEquipos(gestorEquipos);
        generarReporteCorredores(gestorEquipos);
        generarReporteCarreras(gestorCarreras);
        
        JOptionPane.showMessageDialog(null, 
            "Reportes generados exitosamente:\n" +
            "- equipos.csv\n" + 
            "- corredores.csv\n" +
            "- carreras.csv");
    }

    private void generarReporteEquipos(Equipos gestorEquipos) {
        try (FileWriter fw = new FileWriter("equipos.csv")) {
            fw.write("ID,Nombre,Performance,Presupuesto\n");
            for (Equipo e : gestorEquipos.getEquipos()) {
                if (e != null) {
                    fw.write(String.format("%s,%s,%d,%.2f%n",
                        e.getId(),
                        e.getNombre(),
                        e.getPerformance(),
                        e.getPresupuesto()));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al guardar equipos.csv\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarReporteCorredores(Equipos gestorEquipos) {
        try (FileWriter fw = new FileWriter("corredores.csv")) {
            fw.write("ID,Nombre,Numero,Habilidad,Experiencia,Equipo\n");
            for (Equipo e : gestorEquipos.getEquipos()) {
                if (e != null) {
                    for (Corredor c : e.getCorredores()) {
                        if (c != null) {
                            fw.write(String.format("%s,%s,%d,%d,%d,%s%n",
                                c.getId(),
                                c.getNombre(),
                                c.getNumero(),
                                c.getHabilidad(),
                                c.getExperiencia(),
                                e.getId()));
                        }
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Error al guardar corredores.csv\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarReporteCarreras(Carreras gestorCarreras) {
        try (FileWriter fw = new FileWriter("carreras.csv")) {
            fw.write("Carrera,Vueltas,Distancia,Equipo Ganador,Corredor Ganador,Tiempo Total\n");
            
            // Obtener datos de carreras de forma segura
            Carrera[] carreras = gestorCarreras.getHistorialCarreras();
            int total = gestorCarreras.getTotalCarreras();
            
            for (int i = 0; i < total; i++) {
                if (carreras[i] != null) {
                    Carrera c = carreras[i];
                    fw.write(String.format("%s,%d,%.2f,%s,%s,%.2f%n",
                        c.getNombre(),
                        c.getVueltas(),
                        c.getDistanciaTotal(),
                        c.getEquipoGanador(),
                        c.getCorredorGanador(),
                        c.getTiempoGanador()));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Error al guardar carreras.csv\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}