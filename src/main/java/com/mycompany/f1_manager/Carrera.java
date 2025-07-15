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
public class Carrera {
    String nombre;
    int vueltas;
    String[][] resultados; // [equipo, corredor, tiempo, experiencia]

    public Carrera(Equipo[] equipos) {
        nombre = pedirTexto("Nombre de la carrera:");
        vueltas = pedirEntero("Cantidad de vueltas:", 1, 100);
        resultados = new String[contarParticipantes(equipos)][4];
        int i = 0;

        for (Equipo eq : equipos) {
            if (eq != null && eq.principal != null) {
                Corredor d = eq.principal;
                double coef = d.habilidad * 0.20 + d.experiencia * 0.25 + eq.performance * 0.55;
                if (d.habilidad == 10) coef *= 1.05;
                if (d.experiencia < 3) coef *= 0.90;

                double velocidad = 200 * coef;
                double tiempoVuelta = 15 / velocidad;
                double tiempoTotal = tiempoVuelta * vueltas;

                resultados[i++] = new String[]{
                    eq.nombre, d.nombre, String.format("%.2f", tiempoTotal), String.valueOf(d.experiencia)
                };
            }
        }

        ordenarResultados();
    }

    void ordenarResultados() {
        for (int i = 0; i < resultados.length - 1; i++) {
            for (int j = i + 1; j < resultados.length; j++) {
                double tiempoI = Double.parseDouble(resultados[i][2]);
                double tiempoJ = Double.parseDouble(resultados[j][2]);
                if (tiempoJ < tiempoI ||
                    (tiempoJ == tiempoI && Integer.parseInt(resultados[j][3]) > Integer.parseInt(resultados[i][3]))) {
                    String[] temp = resultados[i];
                    resultados[i] = resultados[j];
                    resultados[j] = temp;
                }
            }
        }
    }

    int contarParticipantes(Equipo[] equipos) {
        int count = 0;
        for (Equipo e : equipos) {
            if (e != null && e.principal != null) count++;
        }
        return count;
    }

    static String pedirTexto(String msg) {
        String r;
        do {
            r = JOptionPane.showInputDialog(msg);
        } while (r == null || r.trim().equals(""));
        return r.trim();
    }

    static int pedirEntero(String msg, int min, int max) {
        int num = min - 1;
        while (num < min || num > max) {
            try {
                num = Integer.parseInt(JOptionPane.showInputDialog(msg));
            } catch (Exception e) {
                num = min - 1;
            }
        }
        return num;
    }
}
