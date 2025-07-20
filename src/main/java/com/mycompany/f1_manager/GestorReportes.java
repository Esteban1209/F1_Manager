/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class GestorReportes {
    public void generarReportes(Equipos gestorEquipos, Carreras gestorCarreras) {
        generarReporteEquipos(gestorEquipos);
        generarReporteCorredores(gestorEquipos);
        generarReporteCarreras(gestorCarreras);
        
        JOptionPane.showMessageDialog(null, "Reportes generados:\n- equipos.csv\n- corredores.csv\n- carreras.csv");
    }

    private void generarReporteEquipos(Equipos gestorEquipos) {
        try (FileWriter fw = new FileWriter("equipos.csv")) {
            fw.write("ID,Nombre,Performance,Presupuesto\n");
            for (Equipo e : gestorEquipos.getEquipos()) {
                if (e != null) {
                    fw.write(e.getId() + "," + e.getNombre() + "," + e.getPerformance() + "," + e.getPresupuesto() + "\n");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar equipos.csv");
        }
    }

    private void generarReporteCorredores(Equipos gestorEquipos) {
        try (FileWriter fw = new FileWriter("corredores.csv")) {
            fw.write("ID,Nombre,Numero,Habilidad,Experiencia,Equipo\n");
            for (Equipo e : gestorEquipos.getEquipos()) {
                if (e != null) {
                    for (Corredor c : e.getCorredores()) {
                        if (c != null) {
                            fw.write(c.getId() + "," + c.getNombre() + "," + c.getNumero() + "," + 
                                    c.getHabilidad() + "," + c.getExperiencia() + "," + e.getId() + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar corredores.csv");
        }
    }

    private void generarReporteCarreras(Carreras gestorCarreras) {
        try (FileWriter fw = new FileWriter("carreras.csv")) {
            fw.write("Carrera,Vueltas,Distancia,Equipo Ganador,Corredor Ganador,Tiempo Total\n");
            for (int i = 0; i < gestorCarreras.getTotalCarreras(); i++) {
                Carrera c = gestorCarreras.getHistorialCarreras()[i];
                fw.write(c.getNombre() + "," + c.getVueltas() + "," + c.getDistanciaTotal() + "," + 
                        c.getEquipoGanador() + "," + c.getCorredorGanador() + "," + String.format("%.2f", c.getTiempoGanador()) + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar carreras.csv");
        }
    }
}